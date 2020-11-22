package com.alis.lotcion.ui.fragments.profile

import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel by inject<ProfileViewModel>()

    override fun initialize() {
        setupProfileData()
    }

    private fun setupProfileData() {

    }

    override fun setupListeners() {
        clickEdit()
        clickSettings()
    }

    private fun clickEdit() {
        button_profile_edit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }

    private fun clickSettings() {
        button_profile_settings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
    }

    override fun setupObservers() {

    }
}