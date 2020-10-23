package com.alis.lotcion.di

import com.alis.lotcion.ui.authentication.signin.SignInViewModel
import com.alis.lotcion.ui.authentication.signup.SignUpViewModel
import com.alis.lotcion.ui.authentication.verifycode.VerifyCodeViewModel
import com.alis.lotcion.ui.fragments.addlot.AddLotViewModel
import com.alis.lotcion.ui.fragments.home.HomeViewModel
import com.alis.lotcion.ui.fragments.home.lots.LotsViewModel
import com.alis.lotcion.ui.fragments.profile.ProfileViewModel
import com.alis.lotcion.ui.fragments.profile.editprofile.EditProfileViewModel
import com.alis.lotcion.ui.lot.LotViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var lotcionModule = module {

    viewModel { SignInViewModel() }
    viewModel { SignUpViewModel() }
    viewModel { VerifyCodeViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AddLotViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { LotsViewModel() }
    viewModel { EditProfileViewModel() }
    viewModel { LotViewModel() }
}