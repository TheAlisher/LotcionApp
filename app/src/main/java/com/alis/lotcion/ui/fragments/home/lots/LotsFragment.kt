package com.alis.lotcion.ui.fragments.home.lots

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alis.lotcion.R
import com.alis.lotcion.adapters.LotsAdapter
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.ui.fragments.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.fragment_lots.*
import org.koin.android.ext.android.inject

class LotsFragment : BaseFragment<LotsViewModel>(R.layout.fragment_lots) {

    override val viewModel by inject<LotsViewModel>()

    private val lotsAdapter = LotsAdapter()

    override fun initialize() {
        setupLotsRecycler()
    }

    private fun setupLotsRecycler() {
        recycler_lots.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lotsAdapter
        }
    }

    override fun setupListeners() {
        lotsAdapter.setOnItemClickListener(object : LotsAdapter.OnItemClickListener {
            override fun onLotItemClick(lotID: String) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToLotGraph(lotID)
                )
            }
        })
    }

    override fun observe() {
        fetchLots()

        subscribeToData()
    }

    private fun fetchLots() {
        viewModel.fetchLots(requireArguments().getInt(ARG_TAB_POSITION))
    }

    private fun subscribeToData() {
        viewModel.data.observe(viewLifecycleOwner, {
            lotsAdapter.setList(it)
        })
    }

    companion object {
        val ARG_TAB_POSITION: String = "tab_position"
    }
}