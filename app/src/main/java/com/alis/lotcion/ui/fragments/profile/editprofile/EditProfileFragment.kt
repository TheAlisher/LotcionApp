package com.alis.lotcion.ui.fragments.profile.editprofile

import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import org.koin.android.ext.android.inject

class EditProfileFragment : BaseFragment<EditProfileViewModel>(R.layout.fragment_edit_profile) {

    override val viewModel by inject<EditProfileViewModel>()

    override fun initialize() {

    }

    override fun setupListeners() {

    }

    override fun setupObservers() {

    }
}