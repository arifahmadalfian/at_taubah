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
import com.zackyasgar.at_tauba.R
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment(), IOnPengajianItemClickListener {

    //inisisal dari class Firebase Pengajian
    private var firebasePengajian = PengajianFirestore()

    //array list bertipe <data class Pengajian>
    private var listPengajian: List<Pengajian> = ArrayList()

    private var pengajianAdapter: PengajianAdapter = PengajianAdapter(listPengajian)

    private var contex : Context? = null

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

        loadDataPengajian()

        rv_pengajian.layoutManager = LinearLayoutManager(contex )
        rv_pengajian.adapter = pengajianAdapter

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