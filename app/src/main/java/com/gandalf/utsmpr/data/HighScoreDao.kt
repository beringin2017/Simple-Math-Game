package com.gandalf.utsmpr.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HighScoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addUser(highScore: HighScore)

   @Query( "SELECT * FROM highscore_table ORDER BY id ASC")
   fun readAllData(): LiveData<List<HighScore>>
}