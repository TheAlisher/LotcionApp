package com.alis.lotcion.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.alis.lotcion.data.network.Resource
import com.alis.lotcion.models.Bidder
import com.alis.lotcion.models.Lot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

private const val LOT_CATEGORY_ART = "Исскуство"
private const val LOT_CATEGORY_BOOK = "Книги"
private const val LOT_CATEGORY_HANDIWORK = "Рукоделие"
private const val LOT_CATEGORY_PROPERTY = "Недвижимость"
private const val LOT_CATEGORY_TRANSPORT = "Транспорт"
private const val LOT_CATEGORY_ACCESSORY = "Аксессуары"
private const val LOT_CATEGORY_ELECTRONICS = "Электроника"
private const val LOT_CATEGORY_ANIMAL = "Животные"

class FirebaseRepository(private val db: FirebaseFirestore) {

    private val lotRef = db.collection("lots")

    //TODO: CHANGE THIS LOGIC
    fun fetchFavoriteLots() {
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
    //TODO: CHANGE THIS LOGIC

    fun fetchAllLots(): MutableLiveData<MutableList<Lot>> {
        val data = MutableLiveData<MutableList<Lot>>()
        lotRef
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchAllLots", "${document.id} => ${document.data}")
                }

                data.value = it.toObjects(Lot::class.java)
            }
            .addOnFailureListener {
                Log.w("FetchAllLots", "Error getting document", it)

                data.value = null
            }
        return data
    }

    //region FetchLotsByCategory

    private fun fetchLotsByCategory(category: String): MutableLiveData<MutableList<Lot>> {
        val data = MutableLiveData<MutableList<Lot>>()
        lotRef
            .whereEqualTo("category", category)
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    Log.d("FetchLotsByCategory", "${document.id} => ${document.data}")
                }

                data.value = it.toObjects(Lot::class.java)
            }
            .addOnFailureListener {
                Log.w("FetchLotsByCategory", "Error getting document", it)

                data.value = null
            }
        return data
    }

    fun fetchArtLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_ART)
    }

    fun fetchBookLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_BOOK)
    }

    fun fetchHandiworkLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_HANDIWORK)
    }

    fun fetchPropertyLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_PROPERTY)
    }

    fun fetchTransportLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_TRANSPORT)
    }

    fun fetchAccessoryLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_ACCESSORY)
    }

    fun fetchElectronicsLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_ELECTRONICS)
    }

    fun fetchAnimalLots(): MutableLiveData<MutableList<Lot>> {
        return fetchLotsByCategory(LOT_CATEGORY_ANIMAL)
    }

    //endregion

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