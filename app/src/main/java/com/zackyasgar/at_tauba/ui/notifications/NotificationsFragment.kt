package com.zackyasgar.at_tauba.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.NotificationsAdapter
import com.zackyasgar.at_tauba.model.NotifikasiData


class NotificationsFragment : Fragment() {

    private var db = FirebaseFirestore.getInstance()
    var v: View? = null
    var recyclerView: RecyclerView? = null
    var notifAdapter: NotificationsAdapter? = null
    lateinit var listNotif: MutableList<NotifikasiData>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_notifications, container, false)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getNotifikasi()
    }

    private fun getNotifikasi() {
        db.collection("notifikasi")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                listNotif = ArrayList()
                for (i in it) {
                    listNotif.add(NotifikasiData(
                        "${i.data["title"]}",
                        "${i.data["message"]}"
                    ))
                }
                recyclerView = v?.findViewById(R.id.rv_notifications)
                notifAdapter = NotificationsAdapter(listNotif)
                recyclerView?.adapter = notifAdapter
                recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            .addOnFailureListener {
                Log.w("failureCoy", "Error getting documents.", it)
                Toast.makeText(activity, "Tidak terhubung internet", Toast.LENGTH_SHORT).show()
            }
    }

}

