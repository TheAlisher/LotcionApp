package com.alis.lotcion.ui.fragments.home.lots

import androidx.recyclerview.widget.LinearLayoutManager
import com.alis.lotcion.R
import com.alis.lotcion.adapters.LotsAdapter
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.models.Lot
import com.alis.lotcion.models.getMockData
import com.alis.lotcion.ui.lot.LotFragment
import kotlinx.android.synthetic.main.fragment_lots.*
import org.koin.android.ext.android.inject

class LotsFragment : BaseFragment<LotsViewModel>(R.layout.fragment_lots) {

    override val viewModel by inject<LotsViewModel>()

    private val lotsAdapter = LotsAdapter()

    override fun initialize() {
        createLotsRecycler()
    }

    private fun createLotsRecycler() {
        recycler_lots.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lotsAdapter
        }
    }

    override fun setupListeners() {
        lotsAdapter.setOnItemClickListener(object : LotsAdapter.OnItemClickListener {
            override fun onLotItemClick(item: Lot) {
                LotFragment.start(
                    this@LotsFragment,
                    R.id.action_homeFragment_to_lot_graph,
                    item
                )
            }
        })
    }

    override fun observe() {
        fetchLots()
    }

    private fun fetchLots() {
        lotsAdapter.clear()
        val data = getMockData()
        data.forEach {
            lotsAdapter.add(it)
        }
        //TODO: viewModel.fetchLots(requireArguments().getInt(ARG_TAB_POSITION))
    }

    companion object {
        val ARG_TAB_POSITION: String = "tab_position"
    }
}