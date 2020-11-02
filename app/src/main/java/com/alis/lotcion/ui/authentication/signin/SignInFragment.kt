package com.alis.lotcion.ui.authentication.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*
import org.koin.android.ext.android.inject

class SignInFragment : BaseFragment<SignInViewModel>(R.layout.fragment_sign_in) {

    override val viewModel by inject<SignInViewModel>()

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 33
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overrideOnBackPressed()
    }

    private fun overrideOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun initialize() {
        auth = FirebaseAuth.getInstance()
        configureGoogleSignIn()
    }

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    override fun setupListeners() {
        clickSignInWithGoogle()
        clickSignIn()
        clickDonTHaveAccount()
    }

    private fun clickSignInWithGoogle() {
        button_sign_in_with_google.setOnClickListener {
            signIn()
        }
    }

    private fun clickSignIn() {
        button_sign_in.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_navigation_home)
        }
    }

    private fun clickDonTHaveAccount() {
        button_sign_in_don_t_have_account.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleAuth", "firebaseAuthWithGoogle:" + account.id)

                firebaseAuthWithGoogle(account.idToken!!)

            } catch (e: ApiException) {
                Log.w("GoogleAuth", "Google sign in failed", e)

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("GoogleAuth", "signInWithCredential:success")

                    findNavController().navigate(R.id.action_signInFragment_to_navigation_home)

                } else {
                    Log.w("GoogleAuth", "signInWithCredential:failure", task.exception)

                }
            }
    }

    override fun observe() {

    }
}