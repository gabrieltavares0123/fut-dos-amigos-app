package com.magrathea.app

import com.magrathea.app.fetures.creategame.CreateGameViewModel
import com.magrathea.app.fetures.createprofile.CreatePlayerProfileViewModel
import com.magrathea.app.fetures.loadgames.LoadPagedGamesViewModel
import com.magrathea.app.fetures.showprofile.ShowPlayerProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LoadPagedGamesViewModel(get())
    }
    viewModel {
        CreatePlayerProfileViewModel(get())
    }
    viewModel {
        ShowPlayerProfileViewModel(get())
    }
    viewModel {
        CreateGameViewModel(get(), get())
    }
}