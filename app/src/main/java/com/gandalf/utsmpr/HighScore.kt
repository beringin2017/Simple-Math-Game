package com.gandalf.utsmpr

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HighScore (
    @PrimaryKey val id : String,
    val name: String,
    val type: Int,
    val score: Int
        )

