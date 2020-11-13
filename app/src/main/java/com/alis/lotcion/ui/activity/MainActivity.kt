package com.alis.lotcion.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alis.lotcion.R
import com.alis.lotcion.extensions.gone
import com.alis.lotcion.extensions.visible
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.view.WindowInsetsController as WindowInsetsController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
        isAuthentication()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottom_nav.setupWithNavController(navController)
        addNavControllerDestinationChangedListener()
    }

    private fun addNavControllerDestinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            changedLogicBottomNavigation(destination.id)
            changedLogicStatusBar(destination.id)
        }
    }

    private fun changedLogicBottomNavigation(id: Int) {
        when (id) {
            R.id.signInFragment,
            R.id.signUpFragment,
            R.id.verifyCodeFragment,
            R.id.lotFragment,
            R.id.editProfileFragment,
            R.id.settingsFragment -> {
                bottom_nav.gone()
            }
            R.id.navigation_home,
            R.id.navigation_add_lot -> {
                bottom_nav.visible()
            }
        }
    }

    private fun changedLogicStatusBar(id: Int) {
        when (id) {
            R.id.navigation_home,
            R.id.navigation_profile -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.lotFragment -> {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }

    private fun isAuthentication() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.signInFragment)
        }
    }
}