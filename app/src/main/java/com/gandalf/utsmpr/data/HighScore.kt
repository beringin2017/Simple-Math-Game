package com.gandalf.utsmpr.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highscore_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val score: Int,
    val dateTime: String
)