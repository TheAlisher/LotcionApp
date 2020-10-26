package com.alis.lotcion.ui.lot

import android.app.Activity
import androidx.navigation.Navigation
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
        fun start(activity: Activity, action: Int, item: Lot) {
            this.item = item
            Navigation
                .findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }
}
