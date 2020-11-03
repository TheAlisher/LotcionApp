package com.alis.lotcion.ui.lot

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.models.Lot
import org.koin.android.ext.android.inject

class LotFragment : BaseFragment<LotViewModel>(R.layout.fragment_lot) {

    override val viewModel by inject<LotViewModel>()

    override fun initialize() {

    }

    override fun setupListeners() {

    }

    override fun observe() {

    }

    companion object {
        private var item: Lot? = null
        fun start(fragment: Fragment, action: Int, item: Lot) {
            fragment.findNavController().navigate(action)
            this.item = item
        }
    }
}
