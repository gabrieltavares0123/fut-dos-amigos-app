package com.magrathea.app.main

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.magrathea.app.R

sealed class FutebolDosAmigosDestinations(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int?,
) {
    object ToListGamesView : FutebolDosAmigosDestinations(
        route = "/listGamesView",
        label = "Games",
        icon = R.drawable.ic_stadium_96___
    )

    object ToPlayerProfileView : FutebolDosAmigosDestinations(
        route = "/playerProfileView",
        label = "Profile",
        icon = R.drawable.ic_soccer_96___
    )

    object ToCreatePlayerProfileView : FutebolDosAmigosDestinations(
        route = "/createPlayerProfileView",
        label = "Create profile",
        icon = null
    )

    object ToCreateGameView : FutebolDosAmigosDestinations(
        route = "/createGameView",
        label = "Create game",
        icon = null
    )
}

@Composable
fun FutebolDosAmigosBottomBar(
    navHostController: NavHostController,
) {
    val bottomBarItems = listOf(
        FutebolDosAmigosDestinations.ToListGamesView,
        FutebolDosAmigosDestinations.ToPlayerProfileView,
    )

    NavigationBar {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomBarItems.forEach { destination ->
            NavigationBarItem(
                icon = {
                    destination.icon?.let { resource ->
                        Icon(
                            painter = painterResource(id = resource),
                            contentDescription = null
                        )
                    }
                },
                label = { Text(destination.label) },
                selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                onClick = {
                    navHostController.navigate(destination.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
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