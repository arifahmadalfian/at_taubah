package com.zackyasgar.at_tauba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.adapter.IOnListKegiatanItemClickListener
import com.zackyasgar.at_tauba.adapter.JumatanAdapterVertical
import com.zackyasgar.at_tauba.adapter.KegiatanAdapterVertical
import com.zackyasgar.at_tauba.detail.KegiatanDetailActivity
import com.zackyasgar.at_tauba.model.Jumat
import com.zackyasgar.at_tauba.model.Kegiatan

class KegiatanListActivity : AppCompatActivity(), IOnListKegiatanItemClickListener {

    private var db = FirebaseFirestore.getInstance()
    private var recyclerView: RecyclerView? = null
    private var kegiatanAdapter: KegiatanAdapterVertical? = null
    private var listKegiatan: MutableList<Kegiatan> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan_list)

        recyclerView = findViewById(R.id.rv_kegiatan_vertical)
        kegiatanAdapter = KegiatanAdapterVertical(listKegiatan, this, this)
        recyclerView?.adapter = kegiatanAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Handler().postDelayed({
            getKegiatan()
        }, 2000)
    }

    private fun getKegiatan() {
        db.collection("kegiatan")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    listKegiatan.add(Kegiatan(
                        "${i.data["judul"]}",
                        "${i.data["tanggal"]}",
                        "${i.data["jam"]}",
                        "${i.data["isi"]}"))
                }
                //menutup shimmer effect
                kegiatanAdapter?.showShimmer = false
                kegiatanAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
                Toast.makeText(this, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onListKegiatanItemClick(kegiatan: Kegiatan, position: Int) {
        val intent = Intent(this@KegiatanListActivity, KegiatanDetailActivity::class.java)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_JUDUL, kegiatan.judul)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_TANGGAL, kegiatan.tanggal)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_JAM, kegiatan.jam)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_ISI, kegiatan.isi)
        startActivity(intent)
    }
}