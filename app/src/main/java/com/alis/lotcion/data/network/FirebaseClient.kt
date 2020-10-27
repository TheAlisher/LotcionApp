package com.alis.lotcion.data.network

import android.util.Log
import com.alis.lotcion.models.Bidder
import com.alis.lotcion.models.Lot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClient {

    private val db = Firebase.firestore

    fun fetchFavoriteLots() {
        db.collection("lots")
            .whereEqualTo("liked", true)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchFavoriteLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchFavoriteLots", "Error getting documents: ", it)
            }
    }

    fun fetchAllLots() {
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

    fun fetchArtLots() {

    }

    fun fetchBookLots() {

    }

    fun fetchHandiworkLots() {

    }

    fun fetchPropertyLots() {

    }

    fun fetchTransportLots() {

    }

    fun fetchAccessoryLots() {

    }

    fun fetchElectronicLots() {

    }

    fun fetchAnimalLots() {

    }

    fun addLot(lot: Lot) {
        db.collection("number of lots")
            .document("number of lots")
            .get()
            .addOnSuccessListener {
                val lotID = "lot " + it.get("number of lots").toString()

                db.collection("lots")
                    .document(lotID)
                    .set(lot)
                    .addOnSuccessListener {
                        Log.d("AddLot", "DocumentSnapshot successfully written!")
                        db.collection("number of lots")
                            .document("number of lots")
                            .update("number of lots", FieldValue.increment(1))
                    }
                    .addOnFailureListener { e ->
                        Log.w("AddLot", "Error writing document", e)
                    }
            }
    }

    fun placeBet(lotID: String, bidder: Bidder) {
        db.collection("lots")
            .document(lotID)
            .update("bidders", FieldValue.arrayUnion(bidder))
            .addOnSuccessListener {
                Log.d("PlaceBet", "BetPlaced")
            }
            .addOnFailureListener {
                Log.w("PlaceBet", "Exception$it")
            }
    }
}