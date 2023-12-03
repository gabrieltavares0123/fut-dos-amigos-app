package com.magrathea.app.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FutebolDosAmigosTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navHostController: NavHostController,
    title: String = "Futebol dos amigos",
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (shouldShowBackArrow(navHostController)) {
            {
                IconButton(
                    onClick = { navHostController.popBackStack() }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        } else null


    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon =
        //See later what's happening.
        { navigationIcon?.invoke() },
        scrollBehavior = scrollBehavior,
    )
}

private fun shouldShowBackArrow(
    navHostController: NavHostController
): Boolean {
    return navHostController.previousBackStackEntry != null &&
            navHostController.currentDestination?.route != FutebolDosAmigosDestinations.ToListGamesView.route
            && navHostController.currentDestination?.route != FutebolDosAmigosDestinations.ToPlayerProfileView.route
}