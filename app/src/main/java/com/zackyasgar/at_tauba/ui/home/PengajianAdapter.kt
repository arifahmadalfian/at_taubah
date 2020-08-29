package com.zackyasgar.at_tauba.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R

class PengajianAdapter(var pengajianListItems: List<Pengajian>): RecyclerView.Adapter<PengajianAdapter.PengajianViewHolder>() {

    inner class PengajianViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemPengajianJudul: TextView = itemView.findViewById(R.id.tv_items_pengajian_judul)
        var itemPengajianTanggal: TextView = itemView.findViewById(R.id.tv_items_pengajian_tanggal)
        var itemPengajianJam: TextView = itemView.findViewById(R.id.tv_items_pengajian_jam)

        fun initialize (pengajian: Pengajian) {
            itemPengajianJudul.text = pengajian.judulPengajian
            itemPengajianTanggal.text = pengajian.tanggalPengajian
            itemPengajianJam.text = pengajian.jamPengajian

            // jika list/cardView item di klik
            //itemView.setOnClickListener {
            //    action.onItemclick(pengajian, adapterPosition)
            //}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajianViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.items_row_pengajian, parent, false)
        return PengajianViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PengajianViewHolder, position: Int) {
        holder.initialize(pengajianListItems[position])
    }

    override fun getItemCount(): Int {
        return pengajianListItems.size
    }

}

interface IOnPengajianItemClickListener {
    fun onItemclick(item: Pengajian, position: Int)
}