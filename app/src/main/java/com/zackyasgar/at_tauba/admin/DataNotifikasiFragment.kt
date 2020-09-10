package com.zackyasgar.at_tauba.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth.getInstance
import com.zackyasgar.at_tauba.LoginActivity
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.fragment_data_notifikasi.*
import kotlinx.android.synthetic.main.fragment_data_pengurus.*


class DataNotifikasiFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_notifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_notifikasi_tambah.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_notifikasi_tambah -> {
                getKirimNotifikasi()
            }
        }
    }

    private fun getKirimNotifikasi() {
        // mengambil text dari fragment_data_notifikasi yang sudah di isi
        val pTitle =et_notifikasi_title.text.toString()
        val pDeskripsi = et_notifikasi_deskripsi.text.toString()


        when {
            pTitle.isEmpty() -> et_notifikasi_title.error = "Title tidak boleh kosong"
            pDeskripsi.isEmpty() -> et_notifikasi_deskripsi.error = "Deskripsi tidak boleh kosong"
            else ->  getPushNotifikasi(pTitle, pDeskripsi)
        }
    }

    private fun getPushNotifikasi(pTitle: String, pDeskripsi: String) {
        // menampilkan progress bar
        pg_notifikasi.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val notifikasi = hashMapOf(
            "title" to pTitle,
            "deskripsi" to pDeskripsi
        )

        db.collection("notifikasi")
            .add(notifikasi)
            .addOnSuccessListener { _->

                pg_notifikasi.visibility = View.GONE
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()

                et_notifikasi_title.text = null
                et_notifikasi_deskripsi.text = null

            }
            .addOnFailureListener { _ ->
                pg_notifikasi.visibility = View.GONE
                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }
    }

}