package com.alis.lotcion.ui.authentication.signup

import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.android.ext.android.inject

class SignUpFragment : BaseFragment<SignUpViewModel>(R.layout.fragment_sign_up) {

    override val viewModel by inject<SignUpViewModel>()

    override fun initialize() {

    }

    override fun setupListeners() {
        clickBack()
        clickSignUp()
    }

    private fun clickBack() {
        button_sign_up_back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickSignUp() {
        button_sign_up.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_verifyCodeFragment)
        }
    }

    override fun setupObservers() {

    }
}