package com.magrathea.app.fetures.creategame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.magrathea.app.main.FutebolDosAmigosBottomBar
import com.magrathea.app.main.FutebolDosAmigosDestinations
import com.magrathea.app.main.FutebolDosAmigosTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameView(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    createGameViewModel: CreateGameViewModel = koinViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            FutebolDosAmigosTopBar(
                scrollBehavior = scrollBehavior,
                navHostController = navHostController,
                title = "Create a game"
            )
        },
        bottomBar = {
            FutebolDosAmigosBottomBar(
                navHostController = navHostController
            )
        },
    ) { paddingValues ->
        var gameName by remember { mutableStateOf("") }
        var gameDate by remember { mutableStateOf("") }
        var gameStreet by remember { mutableStateOf("") }
        var gameNumber by remember { mutableStateOf("") }
        var gameCity by remember { mutableStateOf("") }
        var gameState by remember { mutableStateOf("") }

        val createGameViewState by createGameViewModel.createGameViewState.collectAsStateWithLifecycle()
        when (createGameViewState) {
            is CreateGameViewState.Success -> {
                navHostController.navigate(
                    FutebolDosAmigosDestinations.ToListGamesView.route
                )
            }

            is CreateGameViewState.Error -> {}
            is CreateGameViewState.Idle -> {}
            is CreateGameViewState.Loading -> {
                Box(modifier = Modifier) {
                    CircularProgressIndicator()
                }
            }
        }

        Column(
            modifier = modifier.padding(paddingValues),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
        ) {
            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameName,
                    onValueChange = {
                        gameName = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.NameChanged(gameName)
                        )
                    },
                    label = { Text(text = "Name") },
                    isError = createGameViewModel.createGameFormState.nameError != null
                )
                if (createGameViewModel.createGameFormState.nameError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.nameError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameDate,
                    onValueChange = {
                        gameDate = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.DateChanged(gameDate)
                        )
                    },
                    label = { Text(text = "Date") },
                    isError = createGameViewModel.createGameFormState.dateError != null
                )
                if (createGameViewModel.createGameFormState.dateError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.dateError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameStreet,
                    onValueChange = {
                        gameStreet = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.StreetChanged(gameStreet)
                        )
                    },
                    label = { Text(text = "Street") },
                    isError = createGameViewModel.createGameFormState.streetError != null
                )
                if (createGameViewModel.createGameFormState.streetError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.streetError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameNumber,
                    onValueChange = {
                        gameNumber = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.NumberChanged(gameNumber)
                        )
                    },
                    label = { Text(text = "Number") },
                    isError = createGameViewModel.createGameFormState.numberError != null
                )
                if (createGameViewModel.createGameFormState.numberError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.numberError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameCity,
                    onValueChange = {
                        gameCity = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.CityChanged(gameCity)
                        )
                    },
                    label = { Text(text = "City") },
                    isError = createGameViewModel.createGameFormState.cityError != null
                )
                if (createGameViewModel.createGameFormState.cityError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.cityError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = gameState,
                    onValueChange = {
                        gameState = it
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.StateChanged(gameState)
                        )
                    },
                    label = { Text(text = "State") },
                    isError = createGameViewModel.createGameFormState.stateError != null
                )
                if (createGameViewModel.createGameFormState.stateError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createGameViewModel.createGameFormState.stateError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Row {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        createGameViewModel.handleCreateGameFormEvents(
                            CreateGameFormEvent.Submit
                        )
                    }
                ) {
                    Text(text = "Create")
                }
            }
        }
    }
}