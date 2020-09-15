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
import kotlinx.android.synthetic.main.items_row_pengajian.view.*

class PengajianAdapter(var pengajianListItems: List<Pengajian>,
                       var context: Context?,
                       var clickListener: IOnPengajianItemClickListener): RecyclerView.Adapter<PengajianAdapter.PengajianViewHolder>() {

    var showShimmer = true

    inner class PengajianViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_pengajian)
        var judul: TextView = itemView.findViewById(R.id.tv_items_pengajian_judul)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_pengajian_tanggal)
        var jam: TextView = itemView.findViewById(R.id.tv_items_pengajian_jam)
        var images: ImageView = itemView.findViewById(R.id.img_pengajian)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(pengajian: Pengajian, action: IOnPengajianItemClickListener) {
            judul.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            judul.text = pengajian.judulPengajian
            tanggal.text = pengajian.tanggalPengajian
            jam.text = pengajian.jamPengajian
            images.setImageDrawable(context?.getDrawable(R.drawable.ngaji))

            itemView.setOnClickListener{
                action.onPengajianItemClick(pengajian, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengajianViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_row_pengajian, parent, false)
        return PengajianViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengajianViewHolder, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(pengajianListItems[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 4
        return if (showShimmer) SHIMMER_ITEM_NUMBER else pengajianListItems.size
    }

}

interface IOnPengajianItemClickListener {
    fun onPengajianItemClick(item: Pengajian, position: Int)
}
