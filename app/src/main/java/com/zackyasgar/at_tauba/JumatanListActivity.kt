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
import com.zackyasgar.at_tauba.adapter.IOnListJumatanItemClickListener
import com.zackyasgar.at_tauba.adapter.JumatanAdapterVertical
import com.zackyasgar.at_tauba.detail.JumatDetailActivity
import com.zackyasgar.at_tauba.model.Jumat

class JumatanListActivity : AppCompatActivity(), IOnListJumatanItemClickListener {

    private var db = FirebaseFirestore.getInstance()
    private var recyclerView: RecyclerView? = null
    private var jumatanAdapter: JumatanAdapterVertical? = null
    private var listJumatan: MutableList<Jumat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jumatan_list)

        recyclerView = findViewById(R.id.rv_jumatan_vertical)
        jumatanAdapter = JumatanAdapterVertical(listJumatan, this, this)
        recyclerView?.adapter = jumatanAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        Handler().postDelayed({
            getJumatan()
        }, 2000)
    }

    private fun getJumatan() {
        db.collection("jumatan")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    listJumatan.add(Jumat(
                        "${i.data["imam"]}",
                        "${i.data["muadzin"]}",
                        "${i.data["tanggal"]}",
                        "${i.data["jam"]}",
                        "${i.data["isi_khutbah"]}"))
                }
                //menutup shimmer
                jumatanAdapter?.showShimmer = false
                jumatanAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
                Toast.makeText(this, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onListJumatanItemClick(item: Jumat, position: Int) {
        val intent = Intent(this@JumatanListActivity, JumatDetailActivity::class.java)
        intent.putExtra(JumatDetailActivity.JUMATAN_KHOTIB, item.imam)
        intent.putExtra(JumatDetailActivity.JUMATAN_MUADZIN, item.muadzin)
        intent.putExtra(JumatDetailActivity.JUMATAN_TANGGAL, item.tanggal)
        intent.putExtra(JumatDetailActivity.JUMATAN_JAM, item.jam)
        intent.putExtra(JumatDetailActivity.JUMATAN_ISI, item.isi_khutbah)
        startActivity(intent)
    }
}