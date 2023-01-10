package com.gandalf.utsmpr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HighScore::class], version = 1, exportSchema = false)
abstract class HighscoreDatabase: RoomDatabase() {

    abstract fun highScoreDao(): HighScoreDao

    companion object{
        @Volatile
        private var INSTANCE: HighscoreDatabase? = null

        fun getDatabase(context: Context): HighscoreDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HighscoreDatabase::class.java,
                    "highScore_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}