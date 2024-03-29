package com.lofinif.gosporttestapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lofinif.gosporttestapp.databinding.ActivityMainBinding
import com.lofinif.gosporttestapp.ui.GoSportTestApp
import com.lofinif.gosporttestapp.ui.compose.theme.GoToSportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /* val flavor = BuildConfig.FLAVOR
        if (flavor == BuildConfig.UI_SYSTEM_COMPOSE) {
            proceedCompose()
        } else {
            proceedView()
        }*/
        proceedView()
    }

    private fun proceedView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigation = binding.bottomNavigation

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment?
        navController = navHostFragment!!.navController


        appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_menu))
        bottomNavigation.setupWithNavController(navController)
    }


    private fun proceedCompose() {
        setContent {
            GoToSportTheme {
                val navController = rememberNavController()
                GoSportTestApp(navController)
                }
            }
        }
    }

