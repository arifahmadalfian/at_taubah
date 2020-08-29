package com.zackyasgar.at_tauba.admin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.format.DateFormat
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import java.util.*

class DataPengajianFragment : Fragment(), View.OnClickListener{

    val TAG = DataPengajianFragment::class.java.simpleName

    // untuk jam dan tanggal
    var timePickerDialog: TimePickerDialog? = null
    var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_pengajian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pg_pengajian.visibility = View.GONE

        btn_pengajian_tgl.setOnClickListener(this)
        btn_pengajian_jam.setOnClickListener(this)

        btn_pengajian_tambah.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pengajian_tgl -> {
                getTanggalPengajian()
            }

            R.id.btn_pengajian_jam -> {
                getJamPengajian()
            }
            R.id.btn_pengajian_tambah -> {

                // mengambil text dari fragment_data_pengajian yang sudah di isi
                val pTema = et_pengajian_tema.text.toString().trim()
                val pJudul = et_pengajian_judul.text.toString().trim()
                val pTanggal = tv_pengajian_tgl.text.toString().trim()
                val pJam = tv_pengajian_jam.text.toString().trim()
                val pIsi = et_pengajian_isi.text.toString().trim()

                when {
                    pTema.isEmpty() -> et_pengajian_tema.error = "Tema tidak boleh kosong"
                    pJudul.isEmpty() -> et_pengajian_judul.error = "Judul tidak boleh kosong"
                    pTanggal == "Tanggal Pengajian" -> Toast.makeText(context, "Masukan Tanggal", Toast.LENGTH_SHORT).show()
                    pJam == "Waktu Pengajian" -> Toast.makeText(context, "Masukan Jam", Toast.LENGTH_SHORT).show()
                    pIsi.isEmpty() -> et_pengajian_isi.error = "Isi tidak boleh kosong"
                    else ->  getTambahDataPengajian(pTema, pJudul, pTanggal, pJam, pIsi)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTambahDataPengajian(
        pTema: String,
        pJudul: String,
        pTanggal: String,
        pJam: String,
        pIsi: String
    ) {
        // menampilkan progress bar
        pg_pengajian.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val pengajian = hashMapOf(
            "Tema" to pTema,
            "Judul" to pJudul,
            "Tanggal" to pTanggal,
            "Jam" to pJam,
            "Isi" to pIsi
        )

        db.collection("pengajian")
            .add(pengajian)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                pg_pengajian.visibility = View.GONE
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()

                et_pengajian_tema.text = null
                et_pengajian_judul.text = null
                tv_pengajian_tgl.text = "Tanggal Pengajian"
                tv_pengajian_jam.text = "Waktu Pengajian"
                et_pengajian_isi.text = null
            }
            .addOnFailureListener { e ->
                pg_pengajian.visibility = View.GONE
                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }

    }

    private fun getJamPengajian() {
        val calendar = Calendar.getInstance()

        timePickerDialog = TimePickerDialog(
            context as Context, OnTimeSetListener { _, hourOfDay, minute ->
                tv_pengajian_jam.text = ("$hourOfDay:$minute")
            },
            // calender pertamakali di buka
            calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE],
            // cek format 24 jam
            DateFormat.is24HourFormat(context as Context)
        )
        timePickerDialog?.show()
    }

    private fun getTanggalPengajian() {
        val calendar = Calendar.getInstance()
        val c_year = calendar.get(Calendar.YEAR)
        val c_month = calendar.get(Calendar.MONTH)
        val c_day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(
            context as Context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                tv_pengajian_tgl.text = ("$dayOfMonth-${month + 1}-$year")  //ditambah satu karena index MOUNT di mulai dari nol (0-11)
            }, c_year, c_month, c_day
        )
        datePickerDialog?.show()
    }

}


