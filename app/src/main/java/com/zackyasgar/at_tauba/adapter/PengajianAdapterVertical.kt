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
import com.zackyasgar.at_tauba.model.Pengajian

class PengajianAdapterVertical(var pengajian: List<Pengajian>,
                               var context: Context?,
                               var clickListener: IOnListPengajianItemClickListener
): RecyclerView.Adapter<PengajianAdapterVertical.PengajianViewHolderVertical>() {

    var showShimmer = true

    inner class PengajianViewHolderVertical(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_pengajian_vertical)
        var judul: TextView = itemView.findViewById(R.id.tv_items_pengajian_judul_vertical)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_pengajian_tanggal_vertical)
        var jam: TextView = itemView.findViewById(R.id.tv_items_pengajian_jam_vertical)
        var images: ImageView = itemView.findViewById(R.id.img_pengajian_vertical)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(pengajian: Pengajian, action: IOnListPengajianItemClickListener) {
            judul.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            judul.text = pengajian.judulPengajian
            tanggal.text = pengajian.tanggalPengajian
            jam.text = pengajian.jamPengajian
            images.setImageDrawable(context?.getDrawable(R.drawable.ngaji))

            itemView.setOnClickListener{
                action.onListPengajianItemClick(pengajian, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajianViewHolderVertical {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_row_pengajian_vertical, parent, false)
        return PengajianViewHolderVertical(view)
    }

    override fun onBindViewHolder(holder: PengajianViewHolderVertical, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(pengajian[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 10
        return if (showShimmer) SHIMMER_ITEM_NUMBER else pengajian.size
    }

}

interface IOnListPengajianItemClickListener {
    fun onListPengajianItemClick(item: Pengajian, position: Int)
}

