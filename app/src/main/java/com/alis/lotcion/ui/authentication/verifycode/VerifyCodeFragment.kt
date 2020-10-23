package com.alis.lotcion.ui.authentication.verifycode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alis.lotcion.R
import com.alis.lotcion.extensions.hideKeyboard
import com.alis.lotcion.extensions.showToastShort
import com.alis.lotcion.ui.fragments.home.HomeFragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_verify_code.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class VerifyCodeFragment : Fragment() {

    private val viewModel by viewModel<VerifyCodeViewModel>()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mVerificationID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        auth.setLanguageCode("ru")
        sendVerificationCode()
    }

    private fun setupListeners() {
        button_verify_code.setOnClickListener {
            val code = edit_verify_code.text.toString().trim()
            if (code.isNotEmpty()) {
                val credential = PhoneAuthProvider.getCredential(mVerificationID, code)
                signInWithAuthCredential(credential)
            }
        }
    }

    private fun sendVerificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+996704184000",
            60,
            TimeUnit.SECONDS,
            requireActivity(),
            callback
        )
    }

    private val callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("PhoneAuth", "onVerificationCompleted:$credential")

                edit_verify_code.setText(credential.smsCode.toString())
                signInWithAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("PhoneAuth", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            override fun onCodeSent(
                verificationID: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationID, token)
                Log.d("PhoneAuth", "onCodeSent:$verificationID")

                mVerificationID = verificationID
            }
        }

    fun signInWithAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("PhoneAuth", "signInWithCredential:success")
                    showToastShort(requireContext(), R.string.toast_you_are_successfully_signed)

                    HomeFragment.start(
                        requireActivity(),
                        R.id.action_verifyCodeFragment_to_navigation_home
                    )
                    hideKeyboard(requireActivity())

                } else {
                    Log.w("PhoneAuth", "signInWithCredential:failure", task.exception)
                    showToastShort(requireContext(), R.string.toast_failed_to_sign_in_try_again)

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
}