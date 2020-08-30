package com.zackyasgar.at_tauba.database

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class PengajianFirestore {

    private val firebaseFirestore = FirebaseFirestore.getInstance()

    fun getListPengajian(): Task<QuerySnapshot> {
        return firebaseFirestore.collection("pengajian")
            .orderBy("tanggal", Query.Direction.DESCENDING)
            .get()
    }
}