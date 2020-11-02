package com.alis.lotcion.ui.authentication.verifycode

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.extensions.showToastShort
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_verify_code.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class VerifyCodeFragment : BaseFragment<VerifyCodeViewModel>(R.layout.fragment_verify_code) {

    override val viewModel by inject<VerifyCodeViewModel>()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mVerificationID: String

    override fun initialize() {
        auth.setLanguageCode("ru")
        sendVerificationCode()
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

    override fun setupListeners() {
        button_verify_code.setOnClickListener {
            val code = edit_verify_code.text.toString().trim()
            if (code.isNotEmpty()) {
                val credential = PhoneAuthProvider.getCredential(mVerificationID, code)
                signInWithAuthCredential(credential)
            }
        }
    }

    override fun observe() {

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

                    findNavController().navigate(R.id.action_verifyCodeFragment_to_navigation_home)

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