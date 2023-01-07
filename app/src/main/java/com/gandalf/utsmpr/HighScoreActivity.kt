package com.gandalf.utsmpr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class HighScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        val db = Room.databaseBuilder(
            applicationContext,MathAppGameDatabase::class.java,
            "highscore"
        ).fallbackToDestructiveMigration().build()

        db.highScoreDao().getHighScores()
    }
}