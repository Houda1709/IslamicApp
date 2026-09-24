package com.example.islamicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.islamicapp.navigation.Screen
import com.example.islamicapp.screens.*
import com.example.islamicapp.ui.theme.IslamicAppTheme

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val screen: Screen
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicAppTheme {
                IslamicApp()
            }
        }
    }
}

@Composable
fun IslamicApp() {
    val navController = rememberNavController()

    val bottomNavItems = listOf(
        BottomNavItem("Accueil", Icons.Filled.Home, Screen.Home),
        BottomNavItem("Quran", Icons.Filled.List, Screen.Quran),
        BottomNavItem("Azkar", Icons.Filled.Star, Screen.Azkar),
        BottomNavItem("Prières", Icons.Filled.Star, Screen.Prayer)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Quran.route,
        Screen.Azkar.route,
        Screen.Prayer.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentRoute == item.screen.route,
                            onClick = {
                                navController.navigate(item.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.Quran.route) {
                QuranScreen(navController)
            }
            composable(Screen.QuranDetail.route) { backStackEntry ->
                val numero = backStackEntry.arguments?.getString("numero")?.toInt() ?: 114
                QuranDetailScreen(navController, numero)
            }
            composable(Screen.Azkar.route) {
                AzkarScreen(navController)
            }
            composable(Screen.AzkarDetail.route) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId")?.toInt() ?: 0
                AzkarDetailScreen(navController, categoryId)
            }
            composable(Screen.Prayer.route) {
                PrayerScreen()
            }
        }
    }
}