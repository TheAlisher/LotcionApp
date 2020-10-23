package com.alis.lotcion.models

data class Lot(
    var image: Int,
    var name: String,
    var description: String,
    var price: Int,
    var timeLeft: String,
    var isLiked: Boolean = false,
)