package com.alis.lotcion.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alis.lotcion.R
import com.alis.lotcion.extensions.gone
import com.alis.lotcion.extensions.visible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavController()
        isAuthentication()
    }

    private fun setupNavController() {
        bottomNav = findViewById(R.id.bottom_nav)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
        addNavControllerDestinationChangedListener()
    }

    private fun addNavControllerDestinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.signInFragment,
                R.id.signUpFragment,
                R.id.verifyCodeFragment,
                R.id.editProfileFragment,
                R.id.settingsFragment -> {
                    bottomNav.gone()
                }
                R.id.navigation_home,
                R.id.navigation_add_lot,
                R.id.navigation_profile -> {
                    bottomNav.visible()
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                    currentFocus?.clearFocus()
                }
                R.id.lotFragment -> {
                    bottomNav.gone()
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                }
            }
        }
    }

    private fun isAuthentication() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navController.navigate(R.id.signInFragment)
        }
    }
}