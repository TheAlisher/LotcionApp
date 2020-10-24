package com.alis.lotcion.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alis.lotcion.R
import com.alis.lotcion.adapters.LotsAdapter
import com.alis.lotcion.models.Lot
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel by viewModel<ProfileViewModel>()

    private val lotsAdapter = LotsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        createLotsRecycler()
        setupProfileData()
    }

    private fun setupListeners() {
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
                //TODO: open LotFragment
            }
        })
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
}