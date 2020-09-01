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
import com.zackyasgar.at_tauba.database.JumatanFirestore
import com.zackyasgar.at_tauba.model.Jumat


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
        recyclerView = v?.findViewById(R.id.rv_jumatan)
        jumatAdapter = context?.let { JumatAdapter(list, it) }
        recyclerView?.adapter = jumatAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list = ArrayList()

        db.collection("jumatan")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val jumatList = result.toObjects(Jumat::class.java)
                //val j = Jumat(jumatList.toTypedArray.toString())
                //(list as ArrayList<Jumat>).add(j)
                Log.d("Coy", "$jumatList ")

            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
            }

    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJumat(view)
    }

    private fun getJumat(view: View) {
         val query = colection.orderBy("tanggal", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Jumat>()
            .setQuery(query, Jumat::class.java)
            .build()

        adapter = JumatAdapter(options)

        var rv : RecyclerView = view.findViewById(R.id.rv_jumatan)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(v?.context)
        rv.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

 */
}

