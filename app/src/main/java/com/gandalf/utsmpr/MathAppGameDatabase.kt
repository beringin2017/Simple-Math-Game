package com.gandalf.utsmpr

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [HighScore::class], version = 2)
abstract class MathAppGameDatabase : RoomDatabase() {
    abstract fun highScoreDao(): HighScoreDao
}