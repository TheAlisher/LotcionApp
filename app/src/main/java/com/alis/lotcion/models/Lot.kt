package com.alis.lotcion.models

data class Lot(
    var id: String,
    var image: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var category: String? = null,
    var startingPrice: Int? = null,
    var finalPrice: Int? = null,
    var timeLeft: String? = null,
    var bidders: MutableList<Bidder>? = null,
    @field:JvmField
    var isLiked: Boolean = false
)