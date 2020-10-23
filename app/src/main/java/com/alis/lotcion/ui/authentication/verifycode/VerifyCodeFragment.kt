package com.alis.lotcion.ui.authentication.verifycode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alis.lotcion.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class VerifyCodeFragment : Fragment() {

    private val viewModel by viewModel<VerifyCodeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}