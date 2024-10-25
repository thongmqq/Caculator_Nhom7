package com.example.a0306221175

sealed class Screen(val route: String){
    object FirstScreen: Screen(route = "FirstScreen")
    object SecondScreen: Screen(route = "SecondScreen")
}