package com.lofinif.gosporttestapp.ui.compose.navigation

sealed class Destination(val route: String) {
    object Menu: Destination("menu")
}