package com.alis.lotcion.di

import com.alis.lotcion.ui.authentication.signin.SignInViewModel
import com.alis.lotcion.ui.authentication.signup.SignUpViewModel
import com.alis.lotcion.ui.authentication.verifycode.VerifyCodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var lotcionModule = module {

    viewModel { SignInViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { VerifyCodeViewModel() }

}