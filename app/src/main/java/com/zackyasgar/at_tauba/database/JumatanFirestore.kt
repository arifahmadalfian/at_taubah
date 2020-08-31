package com.zackyasgar.at_tauba.database

import android.content.Context
import android.util.Log
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.zackyasgar.at_tauba.model.Jumat

class JumatanFirestore() {

    private var db = FirebaseFirestore.getInstance()

    fun getDataJumat(): List<Jumat> {
        val results: MutableList<Jumat> = ArrayList()
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
                    (results as ArrayList<Jumat>).add(jumat)
                    Log.d("successCoy", "${document.id} => ${document.data["Imam"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("failureCoy", "Error getting documents.", exception)
            }
        return results
    }
}