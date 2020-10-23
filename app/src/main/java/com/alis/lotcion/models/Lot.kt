package com.alis.lotcion.models

data class Lot(
    var image: Int,
    var name: String,
    var description: String,
    var startingPrice: Int,
    var finalPrice: Int,
    var timeLeft: String,
    var bidders: MutableList<Bidder>,
    var isLiked: Boolean = false
)