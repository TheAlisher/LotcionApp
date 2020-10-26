package com.alis.lotcion.ui.fragments.addlot

import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.extensions.showToastShort
import kotlinx.android.synthetic.main.fragment_add_lot.*
import org.koin.android.ext.android.inject

class AddLotFragment : BaseFragment<AddLotViewModel>(R.layout.fragment_add_lot) {

    override val viewModel by inject<AddLotViewModel>()

    override fun initialize() {

    }

    override fun setupListeners() {
        button_add_lot.setOnClickListener {
            showToastShort(requireContext(), "ОТДАЧА")
        }
    }

    override fun observe() {

    }
}