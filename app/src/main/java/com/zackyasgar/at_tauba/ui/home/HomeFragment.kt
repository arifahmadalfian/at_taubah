package com.zackyasgar.at_tauba.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import com.zackyasgar.at_tauba.adapter.PengajianAdapter
import com.zackyasgar.at_tauba.database.PengajianFirestore
import com.zackyasgar.at_tauba.model.Jumat
import com.zackyasgar.at_tauba.model.Pengajian
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment(){

    var pengajianAdapter: PengajianAdapter? = null
    lateinit var listPengajian: MutableList<Pengajian>

    private var db = FirebaseFirestore.getInstance()
    var v: View? = null
    var recyclerView: RecyclerView? = null
    var jumatAdapter: JumatAdapter? = null
    lateinit var list: MutableList<Jumat>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_home, container, false)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPengajian()
    }

    private fun getPengajian() {
        db.collection("pengajian")
            .orderBy("Tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                listPengajian = ArrayList()
                for (i in result){
                    listPengajian.add(Pengajian(
                        "${i.data["Tema"]}",
                        "${i.data["Judul"]}",
                        "${i.data["Tanggal"]}",
                        "${i.data["Jam"]}",
                        "${i.data["Isi"]}"))
                    Log.d("CoyPengajian", "${i.data["Tema"]}")

                }
                recyclerView = v?.findViewById(R.id.rv_pengajian)
                pengajianAdapter = PengajianAdapter(listPengajian)
                recyclerView?.adapter = pengajianAdapter
                recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
            }
    }


}