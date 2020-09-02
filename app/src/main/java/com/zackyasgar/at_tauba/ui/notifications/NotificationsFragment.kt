package com.zackyasgar.at_tauba.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.R
import com.zackyasgar.at_tauba.adapter.JumatAdapter
import com.zackyasgar.at_tauba.adapter.PengajianAdapter
import com.zackyasgar.at_tauba.database.JumatanFirestore
import com.zackyasgar.at_tauba.model.Jumat
import com.zackyasgar.at_tauba.model.Pengajian


class NotificationsFragment : Fragment() {

    private var db = FirebaseFirestore.getInstance()
    private var colection = db.collection("jumatan")
    private lateinit var adapter: JumatAdapter

    var v: View? = null
    var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var database: JumatanFirestore? = null
    var jumatAdapter: JumatAdapter? = null

    lateinit var list: MutableList<Jumat>


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

        getJumat()
    }

    private fun getJumat() {
        db.collection("jumatan")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                list = ArrayList()
                for (i in result){
                    list.add(Jumat("${i.data["imam"]}","${i.data["muadzin"]}","${i.data["tanggal"]}","${i.data["jam"]}","${i.data["isi_khutbah"]}"))
                    Log.d("Coy", "${i.data["imam"]}")

                }
                recyclerView = v?.findViewById(R.id.rv_jumatan)
                jumatAdapter = JumatAdapter(list)
                recyclerView?.adapter = jumatAdapter
                recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
            }
    }

}

