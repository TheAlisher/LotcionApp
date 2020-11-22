package com.alis.lotcion.ui.lot

import androidx.navigation.fragment.navArgs
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_lot.*
import kotlinx.android.synthetic.main.item_lot.*
import org.koin.android.ext.android.inject

class LotFragment : BaseFragment<LotViewModel>(R.layout.fragment_lot) {

    override val viewModel by inject<LotViewModel>()
    private val args: LotFragmentArgs by navArgs()

    override fun initialize() {
    }

    private fun fetchLot() {
        viewModel.fetchLotByID(args.lotID)
    }

    override fun setupListeners() {

    }

    override fun observe() {
        viewModel.fetchLotByID(args.lotID).observe(viewLifecycleOwner, {
            text_lot_id.text = it.id
        })
    }
}
