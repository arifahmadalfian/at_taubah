package com.zackyasgar.at_tauba.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Pengajian

class PengajianAdapter(var mContext: Context, var pengajianListItems: List<Pengajian>): RecyclerView.Adapter<PengajianAdapter.PengajianViewHolder>() {

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
        var view: View? = null
        view = LayoutInflater.from(mContext).inflate(R.layout.items_row_pengajian, parent, false)
        val vHolder = PengajianViewHolder(view)
        return vHolder
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