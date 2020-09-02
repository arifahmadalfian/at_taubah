package com.zackyasgar.at_tauba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Pengurus

class PengurusAdapter(var pengurus: List<Pengurus>): RecyclerView.Adapter<PengurusAdapter.PengurusHolder>() {

    class PengurusHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var nama: TextView = itemView.findViewById(R.id.tv_items_pengurus_nama)
        var jabatan: TextView = itemView.findViewById(R.id.tv_items_pengurus_jabatan)

        fun bind(pengurus: Pengurus) {
            nama.text = pengurus.nama
            jabatan.text = pengurus.jabatan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengurusHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_row_pengurus, parent, false)
        return PengurusHolder(view)
    }

    override fun onBindViewHolder(holder: PengurusHolder, position: Int) {
        holder.bind(pengurus[position])
    }

    override fun getItemCount(): Int {
        return pengurus.size
    }

}