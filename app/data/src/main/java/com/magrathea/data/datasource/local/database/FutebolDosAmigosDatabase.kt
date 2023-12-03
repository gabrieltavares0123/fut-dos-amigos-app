package com.magrathea.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.magrathea.data.datasource.local.database.PlayerDao
import com.magrathea.data.model.local.PlayerEntity

@Database(
    entities = [
        PlayerEntity::class
    ],
    version = 1
)
abstract class FutebolDosAmigosDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}