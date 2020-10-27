package com.alis.lotcion.data.network

import android.util.Log
import com.alis.lotcion.models.Lot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClient {

    private val db = Firebase.firestore

    /*fun fetchAllLots() {
        db.collection("lots")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchLots", "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener {
                Log.w("FetchLots", "Error getting document", it)
            }
    }

    fun addLot(lotID: String, lot: Lot) {
        db.collection("lots")
            .document(lotID)
            .set(lot)
            .addOnSuccessListener {
                Log.d("AddLot", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener {
                Log.w("AddLot", "Error writing document", it)
            }

    }*/
}