package com.magrathea.app.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.magrathea.app.fetures.creategame.CreateGameView
import com.magrathea.app.fetures.loadgames.LoadGamesView
import com.magrathea.app.fetures.createprofile.CreatePlayerProfileView
import com.magrathea.app.fetures.showprofile.ShowPlayerProfileView

@Composable
fun FutebolDosAmigosNavigation() {
    val navHostController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = FutebolDosAmigosDestinations.ToListGamesView.route
    ) {
        composable(FutebolDosAmigosDestinations.ToListGamesView.route) {
            LoadGamesView(
                navHostController = navHostController,
            )
        }
        composable(FutebolDosAmigosDestinations.ToPlayerProfileView.route) {
            ShowPlayerProfileView(
                navHostController = navHostController,
            )
        }
        composable(FutebolDosAmigosDestinations.ToCreatePlayerProfileView.route) {
            CreatePlayerProfileView(
                navHostController = navHostController,
            )
        }
        composable(FutebolDosAmigosDestinations.ToCreateGameView.route) {
            CreateGameView(
                navHostController = navHostController,
            )
        }
    }
}