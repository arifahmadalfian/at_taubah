package com.zackyasgar.at_tauba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Jumat

class JumatAdapter(var jumat: List<Jumat>, context: Context) : RecyclerView.Adapter<JumatAdapter.JumatHolder>(){

    var mContext = context

    inner class JumatHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var imam: TextView = itemView.findViewById(R.id.tv_items_jumat_imam)
        var muadzin: TextView = itemView.findViewById(R.id.tv_items_jumat_muadzin)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_jumat_tanggal)
        var jam: TextView = itemView.findViewById(R.id.tv_items_jumat_jam)
        var isi: TextView = itemView.findViewById(R.id.tv_items_jumat_isi)

        fun initialize(jumat: Jumat){
            imam.text = jumat.imam
            muadzin.text = jumat.muadzin
            tanggal.text = jumat.tanggal
            jam.text = jumat.jam
            isi.text = jumat.isi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JumatHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.items_row_jumatan, parent, false)
        return JumatHolder(view)
    }

    override fun onBindViewHolder(holder: JumatHolder, position: Int) {
        holder.initialize(jumat[position])
    }

    override fun getItemCount(): Int {
        return jumat.size
    }

}