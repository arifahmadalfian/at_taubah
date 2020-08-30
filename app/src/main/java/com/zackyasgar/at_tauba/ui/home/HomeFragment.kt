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
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import com.zackyasgar.at_tauba.adapter.PengajianAdapter
import com.zackyasgar.at_tauba.database.PengajianFirestore
import com.zackyasgar.at_tauba.model.Pengajian
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment(){

    private var jumatData = FirebaseFirestore.getInstance()
    private var jumatList = jumatData.collection("jumatan")
    private lateinit var adapterJumat: JumatAdapter

    //inisisal dari class Firebase Pengajian
    private var firebasePengajian = PengajianFirestore()
    private var listPengajian: List<Pengajian> = ArrayList()
    private var pengajianListAdapter: PengajianAdapter = PengajianAdapter(listPengajian)
    private lateinit var recyclerViewPengajian: RecyclerView
    private var v: View? = null
    private var v1: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         this.v1 = view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPengajian()
    }
    private fun getPengajian() {
        if(firebasePengajian.getUser() == null){
            firebasePengajian.createUser().addOnCompleteListener {
                if(it.isSuccessful){
                    loadDataPengajian()
                } else {
                    Log.d("loadDataPengajian()", "Error : ${it.exception!!.message} ")
                }
            }
        } else {
            loadDataPengajian()
            rv_pengajian.layoutManager = LinearLayoutManager(v?.context, LinearLayoutManager.HORIZONTAL, false)
            rv_pengajian.setHasFixedSize(true)
            rv_pengajian.adapter = pengajianListAdapter
        }
    }

    private fun loadDataPengajian() {
        firebasePengajian.getListPengajian().addOnCompleteListener {
            if (it.isSuccessful) {
                listPengajian = it.result!!.toObjects(Pengajian::class.java)
                pengajianListAdapter.pengajianListItems = listPengajian
                pengajianListAdapter.notifyDataSetChanged()
            } else {
                Log.d("loadDataPengajian()", "Error : ${it.exception!!.message} ")
            }
        }
    }
}