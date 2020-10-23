package com.alis.lotcion.ui.authentication.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val viewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
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
}