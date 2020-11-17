package com.alis.lotcion.ui.lot

import androidx.navigation.fragment.navArgs
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import org.koin.android.ext.android.inject

class LotFragment : BaseFragment<LotViewModel>(R.layout.fragment_lot) {

    override val viewModel by inject<LotViewModel>()

    override fun initialize() {
        fetchLot()
    }

    private fun fetchLot() {
        val args: LotFragmentArgs by navArgs()
        viewModel.fetchLot(args.lotID)
    }

    override fun setupListeners() {

    }

    override fun observe() {

    }
}
