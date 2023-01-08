package com.gandalf.utsmpr

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class HighScoreActivity : AppCompatActivity() {

    private lateinit var hsText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        hsText = findViewById(R.id.highScoreText)
        hsText.text = ""
        val db = Room.databaseBuilder(
            applicationContext,MathAppGameDatabase::class.java,
            "highscore"
        ).fallbackToDestructiveMigration().build()


        GlobalScope.launch {

            val highScores = db.highScoreDao().getHighScores()
            highScores.collect { highScores ->

                val newHighScore = HighScore(UUID.randomUUID().toString(), "raj", 0, 10)
                db.highScoreDao().insertHighScore(newHighScore)
                for (hs in highScores) {
                    runOnUiThread {
                        hsText.append(hs.id + hs.name + "\n"
                                + hs.score + "\n"
                                + hs.type + "\n"
                                + "\n\n")
                    }
                }
            }
        }




    }
}