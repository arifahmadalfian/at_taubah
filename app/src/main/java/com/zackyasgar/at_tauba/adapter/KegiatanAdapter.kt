package com.zackyasgar.at_tauba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Kegiatan

class KegiatanAdapter(var kegiatan: List<Kegiatan>): RecyclerView.Adapter<KegiatanAdapter.KegiatanHolder>() {

    class KegiatanHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var judul: TextView = itemView.findViewById(R.id.tv_items_kegiatan_judul)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_kegiatan_tanggal)
        var jam: TextView = itemView.findViewById(R.id.tv_items_kegiatan_jam)

        fun bind(kegiatan: Kegiatan) {
            judul.text = kegiatan.judul
            tanggal.text = kegiatan.tanggal
            jam.text = kegiatan.jam
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.items_row_kegiatan, parent, false)
        return KegiatanHolder(view)
    }

    override fun onBindViewHolder(holder: KegiatanHolder, position: Int) {
        holder.bind(kegiatan[position])
    }

    override fun getItemCount(): Int {
        return kegiatan.size
    }

}