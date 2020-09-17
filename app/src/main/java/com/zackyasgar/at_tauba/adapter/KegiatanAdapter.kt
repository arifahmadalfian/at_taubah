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
import com.zackyasgar.at_tauba.model.Kegiatan
import com.zackyasgar.at_tauba.ui.home.HomeFragment

class KegiatanAdapter(
    var kegiatan: List<Kegiatan>,
    var context: Context?,
    var clickListener: IOnKegiatanItemClickListener
): RecyclerView.Adapter<KegiatanAdapter.KegiatanHolder>() {

    var showShimmer = true

    inner class KegiatanHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_kegiatan)
        var judul: TextView = itemView.findViewById(R.id.tv_items_kegiatan_judul)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_kegiatan_tanggal)
        var jam: TextView = itemView.findViewById(R.id.tv_items_kegiatan_jam)
        var images: ImageView = itemView.findViewById(R.id.img_kegiatan)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(kegiatan: Kegiatan, action: IOnKegiatanItemClickListener) {
            judul.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            judul.text = kegiatan.judul
            tanggal.text = kegiatan.tanggal
            jam.text = kegiatan.jam
            images.setImageDrawable(context?.getDrawable(R.drawable.kegiatan))

            itemView.setOnClickListener{
                action.onKegiatanClickListener(kegiatan, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.items_row_kegiatan, parent, false)
        return KegiatanHolder(view)
    }

    override fun onBindViewHolder(holder: KegiatanHolder, position: Int) {
        //menampilkan shimmer effect
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(kegiatan[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 4
        return if(showShimmer) SHIMMER_ITEM_NUMBER else kegiatan.size
    }

}

interface IOnKegiatanItemClickListener {
    fun onKegiatanClickListener(kegiatan: Kegiatan, position: Int)
}