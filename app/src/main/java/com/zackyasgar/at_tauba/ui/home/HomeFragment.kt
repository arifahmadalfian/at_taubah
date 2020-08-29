package com.zackyasgar.at_tauba.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import com.zackyasgar.at_tauba.model.Jumat
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment(), IOnPengajianItemClickListener {

    private var jumatData = FirebaseFirestore.getInstance()
    private var jumatList = jumatData.collection("jumatan")
    private lateinit var adapterJumat: JumatAdapter

    //inisisal dari class Firebase Pengajian
    private var firebasePengajian = PengajianFirestore()

    //array list bertipe <data class Pengajian>
    private var listPengajian: List<Pengajian> = ArrayList()

    private var pengajianAdapter: PengajianAdapter = PengajianAdapter(listPengajian)

    var contex : Context? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        contex = view.context
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataJumat(view)

        loadDataPengajian()

        rv_pengajian.layoutManager = LinearLayoutManager(contex, LinearLayoutManager.HORIZONTAL, false)
        rv_pengajian.setHasFixedSize(true)
        rv_pengajian.adapter = pengajianAdapter

    }

    private fun loadDataJumat(view: View) {
        val query: Query = jumatList.orderBy("tanggal",Query.Direction.DESCENDING)

        val options: FirestoreRecyclerOptions<Jumat> = FirestoreRecyclerOptions.Builder<Jumat>()
            .setQuery(query, Jumat::class.java)
            .build()

        adapterJumat = JumatAdapter(options)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_jumatan)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(contex, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapterJumat
    }

    private fun loadDataPengajian() {
        firebasePengajian.getListPengajian().addOnCompleteListener {
            if (it.isSuccessful) {
                listPengajian = it.result!!.toObjects(Pengajian::class.java)
                pengajianAdapter.pengajianListItems = listPengajian
                pengajianAdapter.notifyDataSetChanged()
            } else {
                Log.d("loadDataPengajian()", "Error : ${it.exception!!.message} ")
            }
        }
    }

    override fun onItemclick(item: Pengajian, position: Int) {
        Toast.makeText(context, item.judulPengajian, Toast.LENGTH_SHORT).show()
    }


}