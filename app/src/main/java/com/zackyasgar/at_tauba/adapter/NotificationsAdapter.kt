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
import com.zackyasgar.at_tauba.R.drawable.ic_notifications_black_24dp
import com.zackyasgar.at_tauba.model.NotifikasiData

class NotificationsAdapter(var notifications: List<NotifikasiData>,var context: Context?): RecyclerView.Adapter<NotificationsAdapter.NotificationsHolder>() {

    var showShimmer = true

    inner class NotificationsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_notifications)
        var title: TextView = itemView.findViewById(R.id.tv_notifications_title)
        var message: TextView = itemView.findViewById(R.id.tv_notifications_message)
        var images: ImageView = itemView.findViewById(R.id.img_notifications)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(notifications: NotifikasiData) {
            title.background = null
            message.background = null
            images.background = null

            title.text = notifications.title
            message.text = notifications.message
            images.setImageDrawable(context?.getDrawable(ic_notifications_black_24dp))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.items_row_notifications, parent, false)
        return NotificationsHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        //menampilkan shimmer
        if (showShimmer) {
            holder.shimmerFrameLayout.startShimmer()
        } else {
            //menghntikan shimmer dan mencopot background di textView dan imageView
            holder.shimmerFrameLayout.stopShimmer()
            holder.shimmerFrameLayout.setShimmer(null)

            holder.bind(notifications[position])
        }
    }

    override fun getItemCount(): Int {
        val SHIMMER_ITEM_NUMBER = 16
        return if (showShimmer) SHIMMER_ITEM_NUMBER else notifications.size
    }
}