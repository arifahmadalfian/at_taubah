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
import kotlinx.android.synthetic.main.fragment_data_pengurus.*


class DataPengurusFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_pengurus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_pengurus_tambah.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_pengurus_tambah -> {
                getTambahPengurus()
            }
            R.id.btn_logout_test -> {
                getLogout()
            }
        }
    }

    private fun getLogout() {
        getInstance().signOut()
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        Toast.makeText(activity, " Berhasil Logout", Toast.LENGTH_SHORT).show()
    }

    private fun getTambahPengurus() {
        // mengambil text dari fragment_data_kegiatan yang sudah di isi
        val pPengurus =et_pengurus_nama.text.toString()
        val pJabatan = et_pengurus_jabatan.text.toString()
        val pUmur = et_pengurus_umur.text.toString()

        when {
            pPengurus.isEmpty() -> et_pengurus_jabatan.error = "Pengurus tidak boleh kosong"
            pJabatan.isEmpty() -> et_pengurus_jabatan.error = "Jabatan tidak boleh kosong"
            pUmur.isEmpty() -> et_pengurus_umur.error = "Umur tidak boleh kosong"
            else ->  getTambahDataKegiatan(pPengurus, pJabatan, pUmur)
        }
    }

    private fun getTambahDataKegiatan(pPengurus: String, pJabatan: String, pUmur: String) {
        // menampilkan progress bar
        pg_pengurus.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        val db = FirebaseFirestore.getInstance()

        val pengurus = hashMapOf(
            "pengurus" to pPengurus,
            "jabatan" to pJabatan,
            "umur" to pUmur
        )

        db.collection("pengurus")
            .add(pengurus)
            .addOnSuccessListener { _->

                pg_pengurus.visibility = View.GONE
                Toast.makeText(context, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()

                et_pengurus_nama.text = null
                et_pengurus_jabatan.text = null
                et_pengurus_umur.text = null

            }
            .addOnFailureListener { _ ->
                pg_pengurus.visibility = View.GONE
                Toast.makeText(context, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }
    }

}