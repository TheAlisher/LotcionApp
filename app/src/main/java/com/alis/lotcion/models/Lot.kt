package com.alis.lotcion.models

data class Lot(
    var id: String,
    var image: Int?,
    var name: String?,
    var description: String?,
    var category: String?,
    var startingPrice: Int?,
    var finalPrice: Int?,
    var timeLeft: String?,
    var bidders: MutableList<Bidder>?,
    var isLiked: Boolean = false
)