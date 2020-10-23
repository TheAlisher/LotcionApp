package com.alis.lotcion.models

data class User(
    var image: Int,
    var name: Int,
    var description: Int,
    var location: String,
    var email: String,
    var phoneNumber: String,
    var publishedLots: Int,
    var lotsWon: Int
)