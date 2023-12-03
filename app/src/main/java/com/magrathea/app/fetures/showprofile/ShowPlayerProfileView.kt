package com.magrathea.app.fetures.showprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.magrathea.app.main.FutebolDosAmigosBottomBar
import com.magrathea.app.main.FutebolDosAmigosDestinations
import com.magrathea.app.main.FutebolDosAmigosTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPlayerProfileView(
    modifier: Modifier = Modifier,
    showPlayerProfileViewModel: ShowPlayerProfileViewModel = koinViewModel(),
    navHostController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            FutebolDosAmigosTopBar(
                scrollBehavior = scrollBehavior,
                navHostController = navHostController,
                title = "Profile"
            )
        },
        bottomBar = {
            FutebolDosAmigosBottomBar(
                navHostController = navHostController
            )
        },
    ) { paddingValues ->
        val playerProfileViewState by showPlayerProfileViewModel.showPlayerProfileViewState.collectAsStateWithLifecycle()

        when (playerProfileViewState) {
            is ShowPlayerProfileViewState.Success -> {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row {
                        Text(text = "Name: ${(playerProfileViewState as ShowPlayerProfileViewState.Success).playerData.name}")
                    }
                    Row {
                        Text(text = "Surname: ${(playerProfileViewState as ShowPlayerProfileViewState.Success).playerData.surname}")
                    }
                    Row {
                        Text(text = "Phone number: ${(playerProfileViewState as ShowPlayerProfileViewState.Success).playerData.phoneNumber}")
                    }
                }
            }

            is ShowPlayerProfileViewState.Failure -> {}

            is ShowPlayerProfileViewState.Loading -> {
                Box(modifier = Modifier) {
                    CircularProgressIndicator()
                }
            }

            is ShowPlayerProfileViewState.CreateANewPlayer -> {
                Box(
                    modifier = modifier.padding(paddingValues)
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                    ) {
                        Text(
                            text = "There is no Player profile to be shown. Please, create your Player profile.",
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = { navHostController.navigate(FutebolDosAmigosDestinations.ToCreatePlayerProfileView.route) }) {
                            Text(text = "Create profile", textAlign = TextAlign.Center)
                        }
                    }
                }
            }

            is ShowPlayerProfileViewState.Idle -> {}
        }
    }
}
