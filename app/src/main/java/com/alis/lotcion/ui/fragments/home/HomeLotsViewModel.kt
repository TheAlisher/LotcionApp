package com.alis.lotcion.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import com.alis.lotcion.base.BaseViewModel
import com.alis.lotcion.data.repository.FirebaseRepository
import com.alis.lotcion.models.Lot

class HomeLotsViewModel(private val repository: FirebaseRepository) : BaseViewModel() {

    var data = MutableLiveData<MutableList<Lot>>()

    fun fetchLots(position: Int) {
        when (position) {
            0 -> {
                fetchAllLots()
            }
            1 -> {

            }
            2 -> {

            }
        }
    }

    private fun fetchAllLots() {
        data = repository.fetchAllLots()
    }
}