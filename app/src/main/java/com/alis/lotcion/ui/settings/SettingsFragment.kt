package com.alis.lotcion.ui.settings

import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment<SettingsViewModel>(R.layout.fragment_settings) {

    override val viewModel by inject<SettingsViewModel>()

    override fun initialize() {

    }

    override fun setupListeners() {
        button_settings_sign_out.setOnClickListener {
            Firebase.auth.signOut()
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_global_signInFragment)
        }
    }

    override fun setupObservers() {

    }
}