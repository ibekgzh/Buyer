package com.example.buyerapp.core.navigation.graph

import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(route = "home", default = false)
annotation class HomeNavGraph(
    val start: Boolean = false
)