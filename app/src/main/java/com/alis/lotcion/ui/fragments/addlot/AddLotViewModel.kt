package com.alis.lotcion.ui.fragments.addlot

import com.alis.lotcion.base.BaseViewModel
import com.alis.lotcion.data.repository.FirebaseRepository
import com.alis.lotcion.models.Lot

class AddLotViewModel(private val repository: FirebaseRepository) : BaseViewModel() {

    fun addLot(lot: Lot) {
        repository.addLot(lot)
    }
}