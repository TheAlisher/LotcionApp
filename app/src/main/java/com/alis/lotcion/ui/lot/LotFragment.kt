package com.alis.lotcion.ui.lot

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alis.lotcion.R
import com.alis.lotcion.models.Lot
import org.koin.androidx.viewmodel.ext.android.viewModel

class LotFragment : Fragment() {

    companion object {
        private var item: Lot? = null
        fun start(activity: Activity, action: Int, item: Lot) {
            this.item = item
            Navigation
                .findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }

    private val viewModel by viewModel<LotViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}