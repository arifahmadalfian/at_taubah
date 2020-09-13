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

class JumatAdapter(var jumat: List<Jumat>,var context: Context?) : RecyclerView.Adapter<JumatAdapter.JumatHolder>(){

    var showShimmer = true

    inner class JumatHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_jumatan)
        var imam: TextView = itemView.findViewById(R.id.tv_items_jumatan_imam)
        var tanggal: TextView = itemView.findViewById(R.id.tv_items_jumatan_tanggal)
        var jam: TextView = itemView.findViewById(R.id.tv_items_jumatan_jam)
        var images: ImageView = itemView.findViewById(R.id.img_jumat)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(jumat: Jumat){
            imam.background = null
            tanggal.background = null
            jam.background = null
            images.background = null

            imam.text = jumat.imam
            tanggal.text = jumat.tanggal
            jam.text = jumat.jam
            images.setImageDrawable(context?.getDrawable(R.drawable.jumat))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JumatHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.items_row_jumatan, parent, false)
        return JumatHolder(view)
    }

    override fun onBindViewHolder(holder: JumatHolder, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(jumat[position])
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 4
        return if (showShimmer) SHIMMER_ITEM_NUMBER else jumat.size
    }

}