package com.alis.lotcion.data.network

import android.util.Log
import com.alis.lotcion.models.Bidder
import com.alis.lotcion.models.Lot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClient {

    private val db = Firebase.firestore

    //region fetch

    suspend fun fetchFavoriteLots() {
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

    suspend fun fetchAllLots() {
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

    suspend fun fetchArtLots() {
        db.collection("lots")
            .whereEqualTo("category", "Исскуство")
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

    suspend fun fetchBookLots() {
        db.collection("lots")
            .whereEqualTo("category", "Книги")
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

    suspend fun fetchHandiworkLots() {
        db.collection("lots")
            .whereEqualTo("category", "Рукоделие")
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

    suspend fun fetchPropertyLots() {
        db.collection("lots")
            .whereEqualTo("category", "Недвижимость")
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

    suspend fun fetchTransportLots() {
        db.collection("lots")
            .whereEqualTo("category", "Транспорт")
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

    suspend fun fetchAccessoryLots() {
        db.collection("lots")
            .whereEqualTo("category", "Аксессуары")
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

    suspend fun fetchElectronicLots() {
        db.collection("lots")
            .whereEqualTo("category", "Электроника")
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

    suspend fun fetchAnimalLots() {
        db.collection("lots")
            .whereEqualTo("category", "Животные")
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

    //endregion

    // region action

    fun putLikeUnlikeLot(lotID: String) {
        db.collection("lots")
            .document(lotID)
            .get()
            .addOnSuccessListener {
                val isLiked: Boolean = it.get("liked") as Boolean

                if (isLiked) {
                    unlikeLot(lotID)
                } else {
                    likeLot(lotID)
                }
            }
    }

    private fun likeLot(lotID: String) {
        db.collection("lots")
            .document(lotID)
            .update("liked", true)
            .addOnSuccessListener { Log.d("LikeLot", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { Log.w("LikeLot", "Error updating document", it) }
    }

    private fun unlikeLot(lotID: String) {
        db.collection("lots")
            .document(lotID)
            .update("liked", false)
            .addOnSuccessListener { Log.d("UnlikeLot", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { Log.w("UnlikeLot", "Error updating document", it) }
    }

    fun addLot(lot: Lot) {
        db.collection("number of lots")
            .document("number of lots")
            .get()
            .addOnSuccessListener {
                val lotID = "lot " + it.get("number of lots").toString()

                lot.id = lotID
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

    // endregion
}