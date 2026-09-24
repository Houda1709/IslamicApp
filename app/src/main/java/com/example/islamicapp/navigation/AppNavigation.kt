package com.example.islamicapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Quran : Screen("quran")
    object QuranDetail : Screen("quran_detail/{numero}") {
        fun createRoute(numero: Int) = "quran_detail/$numero"
    }
    object Azkar : Screen("azkar")
    object AzkarDetail : Screen("azkar_detail/{categoryId}") {
        fun createRoute(id: Int) = "azkar_detail/$id"
    }
    object Prayer : Screen("prayer")
}