package com.alis.lotcion.ui.fragments.home

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alis.lotcion.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun start(activity: Activity, action: Int) {
            Navigation
                .findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}