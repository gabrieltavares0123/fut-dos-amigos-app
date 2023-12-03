package com.magrathea.app.fetures.createprofile

sealed class CreatePlayerProfileViewEvent {
    object Submit : CreatePlayerProfileViewEvent()
}