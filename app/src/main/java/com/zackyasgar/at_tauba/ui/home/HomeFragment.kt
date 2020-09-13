package com.zackyasgar.at_tauba.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import com.zackyasgar.at_tauba.adapter.KegiatanAdapter
import com.zackyasgar.at_tauba.adapter.PengajianAdapter
import com.zackyasgar.at_tauba.adapter.PengurusAdapter
import com.zackyasgar.at_tauba.model.Jumat
import com.zackyasgar.at_tauba.model.Kegiatan
import com.zackyasgar.at_tauba.model.Pengajian
import com.zackyasgar.at_tauba.model.Pengurus

class HomeFragment : Fragment(){

    private var db = FirebaseFirestore.getInstance()
    var v: View? = null
    var recyclerView: RecyclerView? = null

    var pengajianAdapter: PengajianAdapter? = null
    var listPengajian: MutableList<Pengajian> = ArrayList()

    var jumatAdapter: JumatAdapter? = null
    var listJumat: MutableList<Jumat> = ArrayList()

    var kegiatanAdapter: KegiatanAdapter? = null
    var listKegiatan: MutableList<Kegiatan> = ArrayList()

    var pengurusAdapter: PengurusAdapter? = null
    var listPengurus: MutableList<Pengurus> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)

        // recycler pengajian
        recyclerView = v?.findViewById(R.id.rv_pengajian)
        pengajianAdapter = PengajianAdapter(listPengajian, context)
        recyclerView?.adapter = pengajianAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler jumatan
        recyclerView = v?.findViewById(R.id.rv_jumatan)
        jumatAdapter = JumatAdapter(listJumat, context)
        recyclerView?.adapter = jumatAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler kegiatan
        recyclerView = v?.findViewById(R.id.rv_kegiatan)
        kegiatanAdapter = KegiatanAdapter(listKegiatan, context)
        recyclerView?.adapter = kegiatanAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler pengurus
        recyclerView = v?.findViewById(R.id.rv_pengurus)
        pengurusAdapter = PengurusAdapter(listPengurus, context)
        recyclerView?.adapter = pengurusAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            getPengajian()
            getJumatan()
            getKegiatan()
            getPengurus()
        }, 2000)
    }

    private fun getPengurus() {
        db.collection("pengurus")
            .orderBy("umur", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    listPengurus.add(Pengurus(
                        "${i.data["pengurus"]}",
                        "${i.data["jabatan"]}",
                        "${i.data["umur"]}"))
                }
                pengurusAdapter?.showShimmer = false
                pengurusAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
                Toast.makeText(activity, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
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
                Toast.makeText(activity, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
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
                Toast.makeText(activity, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getJumatan() {
        db.collection("jumatan")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (i in result){
                    listJumat.add(Jumat("${i.data["imam"]}",
                        "${i.data["muadzin"]}",
                        "${i.data["tanggal"]}",
                        "${i.data["jam"]}",
                        "${i.data["isi_khutbah"]}"))
                }
                //menutup shimmer
                jumatAdapter?.showShimmer = false
                jumatAdapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
                Toast.makeText(activity, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }


}