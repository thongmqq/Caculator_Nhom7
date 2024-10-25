package com.example.a0306221175

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.FirstScreen.route) {
        composable(route = Screen.FirstScreen.route){ FirstScreen(navController = navController)}
        composable(route = Screen.SecondScreen.route){ SecondScreen(navController = navController)}
    }
}