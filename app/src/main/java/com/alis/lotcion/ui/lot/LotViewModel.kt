package com.alis.lotcion.ui.lot

import androidx.lifecycle.MutableLiveData
import com.alis.lotcion.base.BaseViewModel
import com.alis.lotcion.data.repository.FirebaseRepository
import com.alis.lotcion.models.Lot

class LotViewModel(private val repository: FirebaseRepository) : BaseViewModel() {

    fun fetchLotByID(lotID: String): MutableLiveData<Lot> {
        return repository.fetchLotByID(lotID)
    }
}