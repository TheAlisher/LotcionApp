package com.alis.lotcion.ui.fragments.home.lots

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alis.lotcion.R
import com.alis.lotcion.adapters.LotsAdapter
import com.alis.lotcion.models.Lot
import com.alis.lotcion.ui.lot.LotFragment
import kotlinx.android.synthetic.main.fragment_lots.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LotsFragment : Fragment() {

    companion object {
        val ARG_TAB_POSITION: String = "tab_position"
    }

    private val viewModel by viewModel<LotsViewModel>()

    private val lotsAdapter = LotsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lots, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createLotsRecycler()
        setupListeners()
        fetchLots()
    }

    private fun createLotsRecycler() {
        recycler_lots.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lotsAdapter
        }
    }

    private fun setupListeners() {
        lotsAdapter.setOnItemClickListener(object : LotsAdapter.OnItemClickListener {
            override fun onLotItemClick(item: Lot) {
                LotFragment.start(
                    requireActivity(),
                    R.id.action_navigation_home_to_lotFragment,
                    item
                )
            }
        })
    }

    private fun fetchLots() {
        lotsAdapter.clear()
        var lot: Lot? = null
        when (requireArguments().getInt(ARG_TAB_POSITION)) {
            0 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            1 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            2 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            3 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            4 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            5 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            6 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            7 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            8 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
            9 -> {
                lot = Lot(
                    R.drawable.ic_launcher_background,
                    "ЛОТ",
                    "ОПИСАНИЕ ЛОТА",
                    1200,
                    3400,
                    "21ч : 34м : 3с",
                    null,
                    false,
                )
            }
        }
        lotsAdapter.add(lot!!)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
    }
}