package com.gandalf.utsmpr

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HighScoreActivity : AppCompatActivity() {

    private lateinit var hsText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        hsText = findViewById(R.id.highScoreText)
        hsText.text = ""



        GlobalScope.launch {
            val highScore = HighScore(UUID.randomUUID().toString(), "raj",0, 10)
            MathAppGameRepo.db.highScoreDao().getHighScores().collect() {
                    highScores ->
                var highScoresString = ""
                for (hs in highScores) {
                    highScoresString = highScoresString + hs.id + "\n" + hs.name + "\n" + hs.score + "\n" + hs.type + "\n\n"
                }
                withContext(Dispatchers.Main)
                {
                    updateTextString(highScoresString)
                }

                }
            }
        }

        fun updateTextString(textSring : String)
        {
            hsText.text = textSring
        }



    }
