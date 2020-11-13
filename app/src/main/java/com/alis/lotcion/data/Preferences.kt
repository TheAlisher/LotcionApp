package com.alis.lotcion.data

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("lotcion.preferences", Context.MODE_PRIVATE)
}