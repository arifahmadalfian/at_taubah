package com.zackyasgar.at_tauba.admin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_jumat.*
import kotlinx.android.synthetic.main.fragment_data_kegiatan.*
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import kotlinx.android.synthetic.main.fragment_data_pengajian.tv_pengajian_tgl
import java.util.*

class DataKegiatanFragment : Fragment(), View.OnClickListener {

    // untuk jam dan tanggal
    var timePickerDialog: TimePickerDialog? = null
    var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_kegiatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_kegiatan_tgl.setOnClickListener(this)
        btn_kegiatan_jam.setOnClickListener(this)

        btn_kegiatan_tambah.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_kegiatan_tgl -> {
                getTanggalKegiatan()
            }

            R.id.btn_kegiatan_jam -> {
                getJamKegiatan()
            }
            R.id.btn_kegiatan_tambah -> {
                getTambahKegiatan()
            }
        }
    }

    private fun getTambahKegiatan() {
        // mengambil text dari fragment_data_kegiatan yang sudah di isi
        val pJudul =et_kegiatan_judul.text.toString()
        val pTanggal = tv_kegiatan_tgl.text.toString()
        val pJam = tv_kegiatan_jam.text.toString()
        val pIsi = et_kegiatan_isi.text.toString()

        when {
            pJudul.isEmpty() -> et_kegiatan_judul.error = "Judul tidak boleh kosong"
            pTanggal == "Tanggal Kegiatan" -> Toast.makeText(context, "Masukan Tanggal", Toast.LENGTH_SHORT).show()
            pJam == "Waktu Kegiatan" -> Toast.makeText(context, "Masukan Jam", Toast.LENGTH_SHORT).show()
            pIsi.isEmpty() -> et_kegiatan_isi.error = "Isi kegiatan tidak boleh kosong"
            else ->  getTambahDataKegiatan(pJudul, pTanggal, pJam, pIsi)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTambahDataKegiatan(pJudul: String, pTanggal: String, pJam: String, pIsi: String) {
        // menampilkan progress bar
        pg_kegiatan.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val kegiatan = hashMapOf(
            "judul" to pJudul,
            "tanggal" to pTanggal,
            "jam" to pJam,
            "isi" to pIsi
        )

        db.collection("kegiatan")
            .add(kegiatan)
            .addOnSuccessListener { _->

                pg_kegiatan.visibility = View.GONE
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()

                et_kegiatan_judul.text = null
                tv_kegiatan_tgl.text = "Tanggal Kegiatan"
                tv_kegiatan_jam.text = "Waktu Kegiatan"
                et_kegiatan_isi.text = null
            }
            .addOnFailureListener { _ ->
                pg_kegiatan.visibility = View.GONE
                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getJamKegiatan() {
        val calendar = Calendar.getInstance()

        timePickerDialog = TimePickerDialog(
            context as Context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                tv_kegiatan_jam.text = ("$hourOfDay:$minute")
            },
            // calender pertamakali di buka
            calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE],
            // cek format 24 jam
            DateFormat.is24HourFormat(context as Context)
        )

        timePickerDialog?.show()
    }

    private fun getTanggalKegiatan() {
        val calendar = Calendar.getInstance()
        val c_year = calendar.get(Calendar.YEAR)
        val c_month = calendar.get(Calendar.MONTH)
        val c_day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(
            context as Context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                tv_kegiatan_tgl.text = ("$dayOfMonth/${month + 1}/$year")  //ditambah satu karena index MOUNT di mulai dari nol (0-11)
            }, c_year, c_month, c_day
        )

        datePickerDialog?.show()
    }

}