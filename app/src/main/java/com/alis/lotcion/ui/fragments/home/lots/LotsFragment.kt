package com.alis.lotcion.ui.fragments.home.lots

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alis.lotcion.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LotsFragment : Fragment() {

    companion object {
        val ARG_TAB_POSITION: String = "tab_position"
    }

    private val viewModel by viewModel<LotsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lots, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}