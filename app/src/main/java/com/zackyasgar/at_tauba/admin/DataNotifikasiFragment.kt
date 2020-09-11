package com.zackyasgar.at_tauba.admin

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.RetrofitInstance
import com.zackyasgar.at_tauba.model.NotifikasiData
import com.zackyasgar.at_tauba.model.PushNotifikasi
import kotlinx.android.synthetic.main.fragment_data_notifikasi.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DataNotifikasiFragment : Fragment(), View.OnClickListener {

    companion object {
        const val TOPIC = "/topics/myTopic"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_data_notifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseMessaging.getInstance().subscribeToTopic(Companion.TOPIC)
        btn_notifikasi_tambah.setOnClickListener(this)

    }

    private fun sendNotifikasi(notifikasi: PushNotifikasi) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotivication(notifikasi)
            if(response.isSuccessful) {
                Log.d("DataNotifikasiFragment", "Response: ${Gson().toJson(response)}")
            }
        } catch (e: Exception) {
            Log.e("DataNotifikasiFragment", e.toString())
        }
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
        val pTanggal = getTanggal()
        val pJam = getJam()

        when {
            pTitle.isEmpty() -> et_notifikasi_title.error = "Title tidak boleh kosong"
            pDeskripsi.isEmpty() -> et_notifikasi_deskripsi.error = "Deskripsi tidak boleh kosong"
            else ->  getPushNotifikasi(pTitle, pDeskripsi, pTanggal, pJam)
        }
    }

    private fun getJam(): String {
        val calendar = Calendar.getInstance()
        val jam = calendar.get(Calendar.HOUR_OF_DAY)
        val menit = calendar.get(Calendar.MINUTE)

        DateFormat.is24HourFormat(context as Context)

        return "$jam:$menit"
    }

    private fun getTanggal(): String {
        val calendar = Calendar.getInstance()
        val tahun = calendar.get(Calendar.YEAR)
        val bulan = calendar.get(Calendar.MONTH)
        val hari = calendar.get(Calendar.DAY_OF_MONTH)

        return "$tahun/$bulan/$hari"
    }

    private fun getPushNotifikasi(
        pTitle: String,
        pMessage: String,
        pTanggal: String,
        pJam: String
    ) {
        // menampilkan progress bar
        pg_notifikasi.visibility = View.VISIBLE

        //inisialisasi FirebaseFirestore
        //untuk menambahkan data ke FirestoreDatabase
        val db = FirebaseFirestore.getInstance()

        val notifikasi = hashMapOf(
            "title" to pTitle,
            "message" to pMessage,
            "tanggal" to pTanggal,
            "jam" to pJam
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

        //Untuk mengirim notifikasi ke aplikasi
        PushNotifikasi(
            NotifikasiData(pTitle, pMessage),
            Companion.TOPIC
        ).also {
            sendNotifikasi(it)
        }
    }

}