package com.magrathea.app.fetures.loadgames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.magrathea.domain.model.GameModel
import com.magrathea.domain.usecase.LoadPagedGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class LoadPagedGamesViewModel(
    private val loadPagedGamesUseCase: LoadPagedGamesUseCase,
) : ViewModel() {
    private val _gamesState: MutableStateFlow<PagingData<GameModel>> =
        MutableStateFlow(PagingData.empty())
    val gamesState: MutableStateFlow<PagingData<GameModel>> = _gamesState

    init {
        handleEvent(LoadPagedGamesViewEvent.LoadGames)
    }

    fun handleEvent(event: LoadPagedGamesViewEvent) {
        viewModelScope.launch {
            when (event) {
                is LoadPagedGamesViewEvent.LoadGames -> {
                    loadGames()
                }
            }
        }
    }

    private suspend fun loadGames() {
        loadPagedGamesUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _gamesState.value = it
            }
    }
}
