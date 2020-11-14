package com.alis.lotcion.ui.main

import android.view.WindowManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.extensions.gone
import com.alis.lotcion.extensions.setupWithNavController
import com.alis.lotcion.extensions.visible
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment<MainViewModel>(R.layout.fragment_main) {

    override val viewModel by inject<MainViewModel>()
    private var currentNavController: LiveData<NavController>? = null

    override fun initialize() {
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home_graph,
            R.navigation.add_lot_graph,
            R.navigation.profile_nav_graph
        )
        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.nav_host_fragment_main,
            intent = requireActivity().intent
        )
        currentNavController = controller

        addNavControllerDestinationChangedListener()
    }

    private fun addNavControllerDestinationChangedListener() {
        currentNavController?.value?.addOnDestinationChangedListener { controller, destination, arguments ->
            changedCurrentFocus(destination.id)
            changedStatusBar(destination.id)
            changedBottomNavigation(destination.id)
        }
    }

    private fun changedCurrentFocus(id: Int) {
        when (id) {
            R.id.navigation_home_graph -> requireActivity().currentFocus?.clearFocus()
        }
    }

    private fun changedStatusBar(id: Int) {
        when (id) {
            R.id.navigation_home_graph,
            R.id.navigation_profile_graph -> {
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.lotFragment -> {
                requireActivity().window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }

    private fun changedBottomNavigation(id: Int) {
        when (id) {
            R.id.signInFragment,
            R.id.signUpFragment,
            R.id.verifyCodeFragment,
            R.id.lotFragment,
            R.id.editProfileFragment,
            R.id.settingsFragment -> {
                bottom_nav.gone()
            }
            R.id.navigation_home_graph,
            R.id.navigation_add_lot_graph -> {
                bottom_nav.visible()
            }
        }
    }

    override fun setupListeners() {

    }

    override fun observe() {

    }
}