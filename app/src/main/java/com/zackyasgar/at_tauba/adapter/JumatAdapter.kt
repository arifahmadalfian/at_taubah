package com.zackyasgar.at_tauba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Jumat

class JumatAdapter(options: FirestoreRecyclerOptions<Jumat>) : FirestoreRecyclerAdapter<Jumat, JumatAdapter.JumatHolder>(options) {

    class JumatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mImam: TextView = itemView.findViewById(R.id.tv_items_jumat_judul)
        var mMuadzin: TextView = itemView.findViewById(R.id.tv_items_jumat_muadzin)
        var mTanggal: TextView = itemView.findViewById(R.id.tv_items_jumat_tanggal)
        var mJam: TextView = itemView.findViewById(R.id.tv_items_jumat_jam)
        var mIsi: TextView = itemView.findViewById(R.id.tv_items_jumat_isi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JumatHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_row_jumatan, parent, false)
        return JumatHolder(view)
    }

    override fun onBindViewHolder(holder: JumatHolder, position: Int, jumat: Jumat) {
        holder.mImam.text = jumat.imam
        holder.mMuadzin.text = jumat.muadzin
        holder.mTanggal.text = jumat.tanggal
        holder.mJam.text = jumat.jam
        holder.mIsi.text = jumat.isi
    }
}