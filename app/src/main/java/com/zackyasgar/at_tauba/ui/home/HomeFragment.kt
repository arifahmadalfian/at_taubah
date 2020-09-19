package com.zackyasgar.at_tauba.ui.home

import android.content.Intent
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
import com.zackyasgar.at_tauba.JumatanListActivity
import com.zackyasgar.at_tauba.PengajianListActivity
import com.zackyasgar.at_tauba.detail.JumatDetailActivity
import com.zackyasgar.at_tauba.detail.PengajianDetailActivity
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.*
import com.zackyasgar.at_tauba.detail.KegiatanDetailActivity
import com.zackyasgar.at_tauba.detail.PengurusDetailActivity
import com.zackyasgar.at_tauba.model.Jumat
import com.zackyasgar.at_tauba.model.Kegiatan
import com.zackyasgar.at_tauba.model.Pengajian
import com.zackyasgar.at_tauba.model.Pengurus
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment(), IOnPengajianItemClickListener, IOnJumatanItemClickListener, IOnKegiatanItemClickListener, IOnPenurusItemClickListener{

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
            savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.fragment_home, container, false)

        // recycler pengajian
        recyclerView = v?.findViewById(R.id.rv_pengajian)
        pengajianAdapter = PengajianAdapter(listPengajian, context, this)
        recyclerView?.adapter = pengajianAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler jumatan
        recyclerView = v?.findViewById(R.id.rv_jumatan)
        jumatAdapter = JumatAdapter(listJumat, context, this)
        recyclerView?.adapter = jumatAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler kegiatan
        recyclerView = v?.findViewById(R.id.rv_kegiatan)
        kegiatanAdapter = KegiatanAdapter(listKegiatan, context, this)
        recyclerView?.adapter = kegiatanAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        //recycler pengurus
        recyclerView = v?.findViewById(R.id.rv_pengurus)
        pengurusAdapter = PengurusAdapter(listPengurus, context, this)
        recyclerView?.adapter = pengurusAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_list_pengajian_click.setOnClickListener {
            val intent = Intent(context, PengajianListActivity::class.java)
            startActivity(intent)
        }
        tv_list_jumatan_click.setOnClickListener {
            val intent = Intent(context, JumatanListActivity::class.java)
            startActivity(intent)
        }
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
                    listJumat.add(Jumat(
                        "${i.data["imam"]}",
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

    override fun onPengajianItemClick(item: Pengajian, position: Int) {
        val intent = Intent(context, PengajianDetailActivity::class.java)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_TEMA, item.temaPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_JUDUL, item.judulPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_TANGGAL, item.tanggalPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_JAM, item.jamPengajian)
        intent.putExtra(PengajianDetailActivity.PENGAJIAN_ISI, item.isiPengajian)
        startActivity(intent)
    }

    override fun onJumatanItemClick(item: Jumat, position: Int) {
        val intent = Intent(context, JumatDetailActivity::class.java)
        intent.putExtra(JumatDetailActivity.JUMATAN_KHOTIB, item.imam)
        intent.putExtra(JumatDetailActivity.JUMATAN_MUADZIN, item.muadzin)
        intent.putExtra(JumatDetailActivity.JUMATAN_TANGGAL, item.tanggal)
        intent.putExtra(JumatDetailActivity.JUMATAN_JAM, item.jam)
        intent.putExtra(JumatDetailActivity.JUMATAN_ISI, item.isi_khutbah)
        startActivity(intent)
    }

    override fun onKegiatanClickListener(kegiatan: Kegiatan, position: Int) {
        val intent = Intent(context, KegiatanDetailActivity::class.java)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_JUDUL, kegiatan.judul)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_TANGGAL, kegiatan.tanggal)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_JAM, kegiatan.jam)
        intent.putExtra(KegiatanDetailActivity.KEGIATAN_ISI, kegiatan.isi)
        startActivity(intent)
    }

    override fun onPengurusClickListener(item: Pengurus, position: Int) {
        val intent = Intent(context, PengurusDetailActivity::class.java)
        intent.putExtra(PengurusDetailActivity.PENGURUS_NAMA, item.nama)
        intent.putExtra(PengurusDetailActivity.PENGURUS_JABATAN, item.jabatan)
        intent.putExtra(PengurusDetailActivity.PENGURUS_UMUR, item.umur)
        startActivity(intent)
    }


}