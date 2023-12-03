package com.magrathea.app.fetures.loadgames

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.magrathea.app.fetures._util.paging.ErrorMessage
import com.magrathea.app.fetures._util.paging.LoadingNextPageItem
import com.magrathea.app.fetures._util.lifecycle.OnLifecycleEvent
import com.magrathea.app.fetures._util.paging.PageLoader
import com.magrathea.app.main.FutebolDosAmigosBottomBar
import com.magrathea.app.main.FutebolDosAmigosDestinations
import com.magrathea.app.main.FutebolDosAmigosTopBar
import com.magrathea.domain.model.GameModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadGamesView(
    modifier: Modifier = Modifier,
    loadPagedGamesViewModel: LoadPagedGamesViewModel = koinViewModel(),
    navHostController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
                onClick = {
                    navHostController.navigate(FutebolDosAmigosDestinations.ToCreateGameView.route)
                }
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            FutebolDosAmigosTopBar(
                scrollBehavior = scrollBehavior,
                navHostController = navHostController,
                title = "Games"
            )
        },
        bottomBar = {
            FutebolDosAmigosBottomBar(
                navHostController = navHostController
            )
        },
    ) { paddingValues ->
        OnLifecycleEvent { owner, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    loadPagedGamesViewModel.handleEvent(
                        LoadPagedGamesViewEvent.LoadGames
                    )
                }

                else -> {
                    // Nothing now.
                }
            }
        }

        val gamePagingItems: LazyPagingItems<GameModel> =
            loadPagedGamesViewModel.gamesState.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            item { Spacer(modifier = Modifier.padding(4.dp)) }
            if (gamePagingItems.itemCount > 0) {
                item {
                    Filters(
                        {
                            // Apply filter.
                        }
                    )
                }
                items(
                    count = gamePagingItems.itemCount,
                    key = gamePagingItems.itemKey { it.id as Long },
                ) { index ->
                    ItemGame(
                        gameModel = gamePagingItems[index]!!,
                        onClick = {
                            // To game detail.
                        }
                    )
                }
                gamePagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = gamePagingItems.loadState.refresh as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingNextPageItem(modifier = Modifier) }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = gamePagingItems.loadState.append as LoadState.Error
                            item {
                                ErrorMessage(
                                    modifier = Modifier,
                                    message = error.error.localizedMessage!!,
                                    onClickRetry = { retry() })
                            }
                        }
                    }
                }
                item { Spacer(modifier = Modifier.padding(4.dp)) }
            } else {
                item {
                    NoItemToBeShown {
                        navHostController.navigate(FutebolDosAmigosDestinations.ToCreateGameView.route)
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemGame(
    gameModel: GameModel,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(150.dp)
                .fillMaxWidth()
        ) {
            Text(text = gameModel.name)
            Text(text = gameModel.date)
            Text(text = gameModel.owner?.name ?: "no owner")
        }
    }
}

@Composable
private fun NoItemToBeShown(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = "There is no games to be shown. Do you want to create your own game?",
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                onClick.invoke()
            }
        ) {
            Text(text = "Create game", textAlign = TextAlign.Center)
        }
    }
}

@Composable
private fun Filters(
    onClick: () -> Unit,
) {
    val filters = setOf(
        "My games",
        "Enrolled",
        "Not enrolled",
        "Ended",
        "Not ended"
    ).toList()

    LazyRow {
        items(
            items = filters
        ) { filter ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(modifier = Modifier.padding(8.dp), text = filter)
            }
        }
    }
}