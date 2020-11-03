package com.alis.lotcion.data.repository

import android.util.Log
import com.alis.lotcion.models.Bidder
import com.alis.lotcion.models.Lot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository(private val db: FirebaseFirestore) {

    private val lotRef = db.collection("lots")

    suspend fun fetchFavoriteLots() {
        lotRef
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
        lotRef
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchAllLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchAllLots", "Error getting document", it)
            }
    }

    suspend fun fetchArtLots() {
        lotRef
            .whereEqualTo("category", "Исскуство")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchArtLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchArtLots", "Error getting document", it)
            }
    }

    suspend fun fetchBookLots() {
        lotRef
            .whereEqualTo("category", "Книги")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchBookLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchBookLots", "Error getting document", it)
            }
    }

    suspend fun fetchHandiworkLots() {
        lotRef
            .whereEqualTo("category", "Рукоделие")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchHandiworkLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchHandiworkLots", "Error getting document", it)
            }
    }

    suspend fun fetchPropertyLots() {
        lotRef
            .whereEqualTo("category", "Недвижимость")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchPropertyLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchPropertyLots", "Error getting document", it)
            }
    }

    suspend fun fetchTransportLots() {
        lotRef
            .whereEqualTo("category", "Транспорт")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchTransportLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchTransportLots", "Error getting document", it)
            }
    }

    suspend fun fetchAccessoryLots() {
        lotRef
            .whereEqualTo("category", "Аксессуары")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchAccessoryLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchAccessoryLots", "Error getting document", it)
            }
    }

    suspend fun fetchElectronicLots() {
        lotRef
            .whereEqualTo("category", "Электроника")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchElectronicLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchElectronicLots", "Error getting document", it)
            }
    }

    suspend fun fetchAnimalLots() {
        lotRef
            .whereEqualTo("category", "Животные")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchAnimalLots", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener {
                Log.w("FetchAnimalLots", "Error getting document", it)
            }
    }

    fun putLikeUnlikeLot(lotID: String) {
        lotRef
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
        lotRef
            .document(lotID)
            .update("liked", true)
            .addOnSuccessListener { Log.d("LikeLot", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { Log.w("LikeLot", "Error updating document", it) }
    }

    private fun unlikeLot(lotID: String) {
        lotRef
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
        lotRef
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