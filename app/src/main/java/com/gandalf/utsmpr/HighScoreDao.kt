package com.gandalf.utsmpr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HighScoreDao {

    @Query("SELECT * from highscore")
    fun getHighScores() : List<HighScore>

    @Insert
    fun insertHighScore(vararg highScore: HighScore)
}