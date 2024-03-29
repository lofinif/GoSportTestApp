package com.lofinif.gosporttestapp.ui.compose.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lofinif.gosporttestapp.ui.compose.menu.MenuScreen

fun NavGraphBuilder.menuScreen(){
    composable(Destination.Menu.route){
        MenuScreen()
    }
}