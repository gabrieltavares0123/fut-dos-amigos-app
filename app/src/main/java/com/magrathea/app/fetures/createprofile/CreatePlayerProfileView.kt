package com.magrathea.app.fetures.createprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.magrathea.app.main.FutebolDosAmigosBottomBar
import com.magrathea.app.main.FutebolDosAmigosDestinations
import com.magrathea.app.main.FutebolDosAmigosTopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlayerProfileView(
    modifier: Modifier = Modifier,
    createPlayerProfileViewModel: CreatePlayerProfileViewModel = koinViewModel(),
    navHostController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            FutebolDosAmigosTopBar(
                scrollBehavior = scrollBehavior,
                navHostController = navHostController,
                title = "Create profile"
            )
        },
        bottomBar = {
            FutebolDosAmigosBottomBar(
                navHostController = navHostController
            )
        },
    ) { paddingValues ->
        var playerName by remember { mutableStateOf("") }
        var playerSurname by remember { mutableStateOf("") }
        var playerPhoneNumber by remember { mutableStateOf("") }

        val createPlayerProfileViewState by
        createPlayerProfileViewModel.createPlayerProfileViewState.collectAsStateWithLifecycle()
        when (createPlayerProfileViewState) {
            is CreatePlayerProfileViewState.Success -> {
                navHostController.navigate(FutebolDosAmigosDestinations.ToPlayerProfileView.route)
            }

            is CreatePlayerProfileViewState.Failure -> {}

            is CreatePlayerProfileViewState.Loading -> {
                Box(modifier = Modifier) {
                    CircularProgressIndicator()
                }
            }

            is CreatePlayerProfileViewState.Idle -> {}
        }

        Column(
            modifier = modifier.padding(paddingValues),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
        ) {
            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = playerName,
                    onValueChange = {
                        playerName = it
                        createPlayerProfileViewModel.handleCreatePlayerFormEvents(
                            CreatePlayerProfileFormEvent.NameChanged(playerName)
                        )
                    },
                    label = { Text(text = "Name") },
                    isError = createPlayerProfileViewModel.createPlayerProfileFormState.nameError != null
                )
                if (createPlayerProfileViewModel.createPlayerProfileFormState.nameError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createPlayerProfileViewModel.createPlayerProfileFormState.nameError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = playerSurname,
                    onValueChange = {
                        playerSurname = it
                        createPlayerProfileViewModel.handleCreatePlayerFormEvents(
                            CreatePlayerProfileFormEvent.SurnameChanged(playerSurname)
                        )
                    },
                    label = { Text(text = "Surname") },
                    isError = createPlayerProfileViewModel.createPlayerProfileFormState.surnameError != null
                )
                if (createPlayerProfileViewModel.createPlayerProfileFormState.surnameError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createPlayerProfileViewModel.createPlayerProfileFormState.surnameError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Column {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = playerPhoneNumber,
                    onValueChange = {
                        playerPhoneNumber = it
                        createPlayerProfileViewModel.handleCreatePlayerFormEvents(
                            CreatePlayerProfileFormEvent.PhoneNumberChanged(playerPhoneNumber)
                        )
                    },
                    label = { Text(text = "Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = createPlayerProfileViewModel.createPlayerProfileFormState.phoneNumberError != null
                )
                if (createPlayerProfileViewModel.createPlayerProfileFormState.phoneNumberError != null) {
                    Text(
                        modifier = Modifier.padding(8.dp, 2.dp),
                        text = createPlayerProfileViewModel.createPlayerProfileFormState.phoneNumberError?.asString()!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Row {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        createPlayerProfileViewModel.handleCreatePlayerFormEvents(
                            CreatePlayerProfileFormEvent.Submit
                        )
                    }
                ) {
                    Text(text = "Create")
                }
            }
        }
    }
}