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
import com.zackyasgar.at_tauba.model.Jumat

class JumatanAdapterVertical(var jumatan: List<Jumat>,
                             var context: Context?,
                             var clickListener: IOnListJumatanItemClickListener
): RecyclerView.Adapter<JumatanAdapterVertical.JumatanViewHolderVertical>() {

    var showShimmer = true

    inner class JumatanViewHolderVertical(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_jumatan_vertical)
        var imam: TextView = itemView.findViewById(R.id.tv_items_jumatan_imam_vertical)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_jumatan_tanggal_vertical)
        var jam: TextView = itemView.findViewById(R.id.tv_items_jumatan_jam_vertical)
        var images: ImageView = itemView.findViewById(R.id.img_jumatan_vertical)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(jumat: Jumat, action: IOnListJumatanItemClickListener) {
            imam.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            imam.text = jumat.imam
            tanggal.text = jumat.tanggal
            jam.text =jumat.jam
            images.setImageDrawable(context?.getDrawable(R.drawable.jumat))

            itemView.setOnClickListener{
                action.onListJumatanItemClick(jumat, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JumatanViewHolderVertical {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_row_jumatan_vertical, parent, false)
        return JumatanViewHolderVertical(view)
    }

    override fun onBindViewHolder(holder:JumatanViewHolderVertical, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(jumatan[position], clickListener)
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 10
        return if (showShimmer) SHIMMER_ITEM_NUMBER else jumatan.size
    }

}

interface IOnListJumatanItemClickListener {
    fun onListJumatanItemClick(item: Jumat, position: Int)
}

