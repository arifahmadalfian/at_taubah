package com.zackyasgar.at_tauba.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    lateinit var jumatList: List<Jumat>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_notifications, container, false)
        recyclerView = v?.findViewById(R.id.rv_jumatan)
        jumatAdapter = context?.let { JumatAdapter(jumatList, it) }
        recyclerView?.adapter = jumatAdapter
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        jumatList = ArrayList()
        (jumatList as ArrayList<Jumat>).add(Jumat("a","a","a","a","a"))
        (jumatList as ArrayList<Jumat>).add(Jumat("b","b","b","b","b"))
        (jumatList as ArrayList<Jumat>).add(Jumat("c","c","c","c","c"))
        (jumatList as ArrayList<Jumat>).add(Jumat("c","d","d","d","d"))
        /*
        db.collection("jumatan").orderBy("Tanggal", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val jumat = Jumat(
                        imam = "${document.data["Imam"]}",
                        muadzin = "${document.data["Muadzin"]}",
                        tanggal = "${document.data["Tanggal"]}",
                        jam = "${document.data["Jam"]}",
                        isi = "${document.data["Isi Khutbah"]}"
                    )
                    (jumatList as ArrayList<Jumat>).add(jumat)
                    Log.d("successCoy", "${document.id} => ${document.data["Imam"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
            }

         */

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