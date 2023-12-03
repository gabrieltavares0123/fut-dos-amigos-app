package com.magrathea.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.magrathea.data.model.local.PlayerEntity

@Dao
interface PlayerDao {
    @Insert
    fun insertLocalPlayer(playerEntity: PlayerEntity)

    @Query("SELECT * FROM PlayerEntity")
    fun loadLocalPlayerProfile(): PlayerEntity?

    @Query("SELECT EXISTS(SELECT * FROM PlayerEntity)")
    fun checkIfThereIsALocalPlayerProfile(): Boolean
}