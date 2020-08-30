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

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }


}