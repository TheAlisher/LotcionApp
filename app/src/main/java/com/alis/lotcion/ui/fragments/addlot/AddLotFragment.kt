package com.alis.lotcion.ui.fragments.addlot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alis.lotcion.R
import com.alis.lotcion.extensions.showToastShort
import kotlinx.android.synthetic.main.fragment_add_lot.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddLotFragment : Fragment() {

    private val viewModel by viewModel<AddLotViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_lot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        button_add_lot.setOnClickListener {
            showToastShort(requireContext(), "ОТДАЧА")
        }
    }
}