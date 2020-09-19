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
import com.zackyasgar.at_tauba.adapter.IOnListPengajianItemClickListener
import com.zackyasgar.at_tauba.adapter.PengajianAdapterVertical
import com.zackyasgar.at_tauba.detail.PengajianDetailActivity
import com.zackyasgar.at_tauba.model.Pengajian

class PengajianListActivity : AppCompatActivity(), IOnListPengajianItemClickListener {

    private var db = FirebaseFirestore.getInstance()
    private var recyclerView: RecyclerView? = null
    private var pengajianAdapter: PengajianAdapterVertical? = null
    private var listPengajian: MutableList<Pengajian> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajian_list)

        recyclerView = findViewById(R.id.rv_pengajian_vertical)
        pengajianAdapter = PengajianAdapterVertical(listPengajian, this, this)
        recyclerView?.adapter = pengajianAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Handler().postDelayed({
            getPengajian()
        }, 2000)
    }

    private fun getPengajian() {
        db.collection("pengajian")
            .orderBy("Tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    listPengajian.add(Pengajian(
                        "${i.data["Tema"]}",
                        "${i.data["Judul"]}",
                        "${i.data["Tanggal"]}",
                        "${i.data["Jam"]}",
                        "${i.data["Isi"]}"))
                }
                //menutup shimmer
                pengajianAdapter?.showShimmer = false
                pengajianAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
                Toast.makeText(this, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onListPengajianItemClick(item: Pengajian, position: Int) {
        val intent = Intent(this@PengajianListActivity, PengajianDetailActivity::class.java)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_TEMA, item.temaPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_JUDUL, item.judulPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_TANGGAL, item.tanggalPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_JAM, item.jamPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_ISI, item.isiPengajian)
        startActivity(intent)
    }
}