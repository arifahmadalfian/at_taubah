package com.zackyasgar.at_tauba.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.Pengurus

class PengurusAdapter(var pengurus: List<Pengurus>,
                      var context: Context?,
                      val clickListener: IOnPenurusItemClickListener): RecyclerView.Adapter<PengurusAdapter.PengurusHolder>() {

    var showShimmer = true

    inner class PengurusHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_pengurus)
        var nama: TextView = itemView.findViewById(R.id.tv_items_pengurus_nama)
        var jabatan: TextView = itemView.findViewById(R.id.tv_items_pengurus_jabatan)
        var image: ImageView = itemView.findViewById(R.id.img_pengurus)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(pengurus: Pengurus, action: IOnPenurusItemClickListener) {
            nama.background = null
            jabatan.background = null
            image.background = null

            nama.text = pengurus.nama
            jabatan.text = pengurus.jabatan
            image.setImageDrawable(context?.getDrawable(R.drawable.dkm))

            itemView.setOnClickListener {
                action.onPengurusClickListener(pengurus, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengurusHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_row_pengurus, parent, false)
        return PengurusHolder(view)
    }

    override fun onBindViewHolder(holder: PengurusHolder, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(pengurus[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 4
        return if (showShimmer) SHIMMER_ITEM_NUMBER else pengurus.size
    }

}

interface IOnPenurusItemClickListener {
        fun onPengurusClickListener(item: Pengurus, position: Int)
}
