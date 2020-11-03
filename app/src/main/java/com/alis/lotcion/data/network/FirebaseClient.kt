package com.alis.lotcion.data.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClient {

    fun provideFirebase() = Firebase.firestore
}