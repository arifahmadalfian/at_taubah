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

class KegiatanAdapterVertical(var kegiatan: List<Kegiatan>,
                              var context: Context,
                              var clickListener: IOnListKegiatanItemClickListener
): RecyclerView.Adapter<KegiatanAdapterVertical.KegiatanViewHolderVertical>(){

    var showShimmer = true

    inner class KegiatanViewHolderVertical(itemView:View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_kegiatan_vertical)
        var judul: TextView = itemView.findViewById(R.id.tv_items_kegiatan_judul_vertical)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_kegiatan_tanggal_vertical)
        var jam: TextView = itemView.findViewById(R.id.tv_items_kegiatan_jam_vertical)
        var images: ImageView = itemView.findViewById(R.id.img_kegiatan_vertical)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(_kegiatan: Kegiatan, action: IOnListKegiatanItemClickListener) {
            judul.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            judul.text = _kegiatan.judul
            tanggal.text = _kegiatan.tanggal
            jam.text = _kegiatan.jam
            images.setImageDrawable(context.getDrawable(R.drawable.kegiatan))

            itemView.setOnClickListener{
                action.onListKegiatanItemClick(_kegiatan, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolderVertical {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_row_kegiatan_vertical, parent, false)
        return KegiatanViewHolderVertical(view)
    }

    override fun onBindViewHolder(holder: KegiatanViewHolderVertical, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(kegiatan[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 10
        return if (showShimmer) SHIMMER_ITEM_NUMBER else kegiatan.size
    }
}

interface IOnListKegiatanItemClickListener {
    fun onListKegiatanItemClick(item: Kegiatan, position: Int)
}