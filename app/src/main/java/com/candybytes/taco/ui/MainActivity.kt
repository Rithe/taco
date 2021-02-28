package com.candybytes.taco.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.candybytes.taco.R
import com.candybytes.taco.databinding.MainActivityBinding
import com.candybytes.taco.ui.vm.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels()

  private lateinit var appBarConfiguration: AppBarConfiguration

  private lateinit var bottomNavigation: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding: MainActivityBinding =
        DataBindingUtil.setContentView(this, R.layout.main_activity)

    binding.apply {
      // Specify the current activity as the lifecycle owner.
      viewModel = this@MainActivity.viewModel
      lifecycleOwner = this@MainActivity
      this@MainActivity.bottomNavigation = navBottomBar

      setupNavigationController()
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment)
    return navController.navigateUp(appBarConfiguration)
        || super.onSupportNavigateUp()
  }

  private fun MainActivityBinding.setupNavigationController() {
    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    appBarConfiguration = AppBarConfiguration(navController.graph)

    toolbar.setupWithNavController(navController, appBarConfiguration)
    navBottomBar.setupWithNavController(navController)
    defineBottomBarVisibility(navController)
  }

  private fun MainActivityBinding.defineBottomBarVisibility(navController: NavController) {
    navController.addOnDestinationChangedListener { _, destination, _ ->
      when (destination.id) {
        R.id.categoriesFragment -> navBottomBar.visibility = View.VISIBLE
        R.id.searchFoodFragment -> navBottomBar.visibility = View.VISIBLE
        else -> navBottomBar.visibility = View.GONE
      }
    }
  }
}
