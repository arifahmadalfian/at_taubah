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
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import kotlinx.android.synthetic.main.fragment_data_jumat.*
import kotlinx.android.synthetic.main.fragment_data_pengajian.*
import java.util.*


class DataJumatFragment : Fragment(), View.OnClickListener {

    // untuk jam dan tanggal
    var timePickerDialog: TimePickerDialog? = null
    var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_jumat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_jumat_tgl.setOnClickListener(this)
        btn_jumat_jam.setOnClickListener(this)

        btn_jumat_tambah.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_jumat_tambah -> {
                // mengambil text dari fragment_data_pengajian yang sudah di isi
                val pImam = et_jumat_imam.text.toString().trim()
                val pMuadzin = et_jumat_muadzin.text.toString().trim()
                val pTanggal = tv_jumat_tgl.text.toString().trim()
                val pJam = tv_jumat_jam.text.toString().trim()
                val pIsi = et_jumat_isi_khutbah.text.toString().trim()

                when {
                    pImam.isEmpty() -> et_jumat_imam.error = "Imam tidak boleh kosong"
                    pMuadzin.isEmpty() -> et_jumat_muadzin.error = "Muadzin tidak boleh kosong"
                    pTanggal == "Tanggal Jum\'atan" -> Toast.makeText(context, "Masukan Tanggal", Toast.LENGTH_SHORT).show()
                    pJam == "Waktu Jum\'at" -> Toast.makeText(context, "Masukan Jam", Toast.LENGTH_SHORT).show()
                    pIsi.isEmpty() -> et_jumat_isi_khutbah.error = "Isi khutbah tidak boleh kosong"
                    else ->  getTambahDataJumat(pImam, pMuadzin, pTanggal, pJam, pIsi)
                }
            }
            R.id.btn_jumat_tgl -> {
                val calendar = Calendar.getInstance()
                val c_year = calendar.get(Calendar.YEAR)
                val c_month = calendar.get(Calendar.MONTH)
                val c_day = calendar.get(Calendar.DAY_OF_MONTH)

                datePickerDialog = DatePickerDialog(
                    context as Context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        tv_jumat_tgl.text = ("$dayOfMonth-${month + 1}-$year")  //ditambah satu karena index MOUNT di mulai dari nol (0-11)
                    }, c_year, c_month, c_day
                )
                datePickerDialog?.show()
            }

            R.id.btn_jumat_jam -> {
                val calendar = Calendar.getInstance()

                timePickerDialog = TimePickerDialog(
                    context as Context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        tv_jumat_jam.text = ("$hourOfDay:$minute")
                    },
                    // calender pertamakali di buka
                    calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE],
                    // cek format 24 jam
                    DateFormat.is24HourFormat(context as Context)
                )
                timePickerDialog?.show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getTambahDataJumat(pImam: String, pMuadzin: String, pTanggal: String, pJam: String, pIsi: String) {
        // menampilkan progress bar
        pg_jumatan.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val jumatan = hashMapOf(
            "imam" to pImam,
            "muadzin" to pMuadzin,
            "tanggal" to pTanggal,
            "jam" to pJam,
            "isi_khutbah" to pIsi
        )

        db.collection("jumatan")
            .add(jumatan)
            .addOnSuccessListener { _->

                pg_jumatan.visibility = View.GONE
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()

                et_jumat_imam.text = null
                et_jumat_muadzin.text = null
                tv_jumat_tgl.text = "Tanggal Jum'atan"
                tv_jumat_jam.text = "Waktu Jum'at"
                et_jumat_isi_khutbah.text = null
            }
            .addOnFailureListener { _ ->
                pg_pengajian.visibility = View.GONE
                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }
    }

}