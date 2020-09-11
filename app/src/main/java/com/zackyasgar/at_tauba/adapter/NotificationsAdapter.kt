package com.zackyasgar.at_tauba.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.model.NotifikasiData

class NotificationsAdapter(var notifications: List<NotifikasiData>): RecyclerView.Adapter<NotificationsAdapter.NotificationsHolder>() {

    class NotificationsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.tv_notifications_title)
        var message: TextView = itemView.findViewById(R.id.tv_notifications_message)

        fun bind(notifications: NotifikasiData) {
            title.text = notifications.title
            message.text = notifications.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.items_row_notifications, parent, false)
        return NotificationsHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsHolder, position: Int) {
        holder.bind(notifications[position])
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}