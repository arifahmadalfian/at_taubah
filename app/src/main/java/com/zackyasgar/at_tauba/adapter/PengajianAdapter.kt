package com.zackyasgar.at_tauba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Pengajian
import kotlinx.android.synthetic.main.items_row_pengajian.view.*

class PengajianAdapter(var pengajianListItems: List<Pengajian>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PengajianViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pengajian: Pengajian) {
            itemView.tv_items_pengajian_judul.text = pengajian.judulPengajian
            itemView.tv_items_pengajian_tanggal.text = pengajian.tanggalPengajian
            itemView.tv_items_pengajian_jam.text = pengajian.jamPengajian
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_row_pengajian, parent, false)
        return PengajianViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PengajianViewHolder).bind(pengajianListItems[position])
    }

    override fun getItemCount(): Int {
        return pengajianListItems.size
    }

}
