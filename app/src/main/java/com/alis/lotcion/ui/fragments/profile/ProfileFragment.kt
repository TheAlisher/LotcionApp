package com.alis.lotcion.ui.fragments.profile

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alis.lotcion.R
import com.alis.lotcion.adapters.LotsAdapter
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.models.Lot
import com.alis.lotcion.ui.lot.LotFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel by inject<ProfileViewModel>()

    private val lotsAdapter = LotsAdapter()

    override fun initialize() {
        createLotsRecycler()
        setupProfileData()
    }

    private fun createLotsRecycler() {
        recycler_profile.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lotsAdapter
        }
    }

    private fun setupProfileData() {
        lotsAdapter.clear()
        val lot = Lot(
            R.drawable.ic_launcher_background,
            "ЛОТ",
            "ОПИСАНИЕ ЛОТА",
            1200,
            3400,
            "21ч : 34м : 3с",
            null,
            false,
        )
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
        lotsAdapter.add(lot)
    }

    override fun setupListeners() {
        clickEdit()
        clickSettings()
        clickLotItem()
    }

    private fun clickEdit() {
        button_profile_edit.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }
    }

    private fun clickSettings() {
        button_profile_settings.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
        }
    }

    private fun clickLotItem() {
        lotsAdapter.setOnItemClickListener(object : LotsAdapter.OnItemClickListener {
            override fun onLotItemClick(item: Lot) {
                LotFragment.start(
                    requireActivity(),
                    R.id.action_navigation_profile_to_lotFragment,
                    item
                )
            }
        })
    }

    override fun observe() {

    }
}