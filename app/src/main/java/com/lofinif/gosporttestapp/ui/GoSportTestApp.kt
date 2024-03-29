package com.lofinif.gosporttestapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.lofinif.gosporttestapp.ui.compose.navigation.Destination
import com.lofinif.gosporttestapp.ui.compose.navigation.menuScreen

@Composable
fun GoSportTestApp(navController: NavHostController){
    NavHost(navController = navController, startDestination = Destination.Menu.route){
        menuScreen ()
    }
}