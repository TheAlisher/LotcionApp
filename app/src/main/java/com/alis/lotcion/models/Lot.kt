package com.alis.lotcion.models

import com.alis.lotcion.R

data class Lot(
    var image: Int?,
    var name: String?,
    var description: String?,
    var startingPrice: Int?,
    var finalPrice: Int?,
    var timeLeft: String?,
    var bidders: MutableList<Bidder>?,
    var isLiked: Boolean = false
)

fun getMockOneData(): Lot {
    return Lot(
        R.drawable.icon_edit_24,
        "ЛОТ",
        "ОПИСАНИЕ ЛОТА",
        1200,
        3400,
        "21ч : 34м : 3с",
        null,
        false,
    )
}

fun getMockData(): MutableList<Lot> {
    return mutableListOf<Lot>().apply {
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
        add(
            Lot(
                R.drawable.ic_launcher_background,
                "ЛОТ",
                "ОПИСАНИЕ ЛОТА",
                1200,
                3400,
                "21ч : 34м : 3с",
                null,
                false,
            )
        )
    }
}