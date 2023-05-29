package com.gandalf.simpleMathGame

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class HighScoreDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score_display)

        val intent = intent
        val judul = intent.getStringExtra("judul")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = judul
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toolbar.setSubtitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setNavigationOnClickListener{
            finish()
        }

        val usernameDivisor = findViewById<TextView>(R.id.playerNameDivisor)
        val scoreDivisor = findViewById<TextView>(R.id.ScoreDivisor)
        val datetimeDivisor = findViewById<TextView>(R.id.dateTimeDivisor)
        val usernameOprmath = findViewById<TextView>(R.id.playerNameOpm)
        val scoreOprmath = findViewById<TextView>(R.id.ScoreOpm)
        val datetimeOprmath = findViewById<TextView>(R.id.dateTimeOpm)
        val usernameSio = findViewById<TextView>(R.id.playerNameSio)
        val scoreSio = findViewById<TextView>(R.id.ScoreSio)
        val datetimeSio = findViewById<TextView>(R.id.dateTimeSio)

        var db = MyDBHelper(this)

        val divisorList = db.getDataDivisor()

        val oprmathList = db.getDataOprmath()

        val sioList = db.getDataSio()

        if (divisorList.size == 0){
            Toast.makeText(this,"Data Divisor Kosong", Toast.LENGTH_SHORT).show()
        }
        else{
            var highScoreDivisor = divisorList.get(0).score
            for (n in 0 until divisorList.size){
                if (highScoreDivisor < divisorList.get(n).score){
                    highScoreDivisor = divisorList.get(n).score
                }
            }

            for (n in 0 until divisorList.size){
                if (divisorList.get(n).score == highScoreDivisor){
                    usernameDivisor.text = divisorList.get(n).username
                    scoreDivisor.text = divisorList.get(n).score.toString()
                    datetimeDivisor.text = divisorList.get(n).datetime
                }
            }
        }

        if (oprmathList.size == 0){
            Toast.makeText(this,"Data OPRMath Kosong", Toast.LENGTH_SHORT).show()
        }
        else{
            var highScoreOprmath = oprmathList.get(0).score
            for (n in 0 until oprmathList.size){
                if (highScoreOprmath < oprmathList.get(n).score){
                    highScoreOprmath = oprmathList.get(n).score
                }
            }

            for (n in 0 until oprmathList.size){
                if (oprmathList.get(n).score == highScoreOprmath){
                    usernameOprmath.text = oprmathList.get(n).username
                    scoreOprmath.text = oprmathList.get(n).score.toString()
                    datetimeOprmath.text = oprmathList.get(n).datetime
                }
            }
        }

        if (sioList.size == 0){
            Toast.makeText(this,"Data SIO Kosong", Toast.LENGTH_SHORT).show()
        }
        else{
            var highScoreSio = sioList.get(0).score
            for (n in 0 until sioList.size){
                if (highScoreSio < sioList.get(n).score){
                    highScoreSio = sioList.get(n).score
                }
            }

            for (n in 0 until sioList.size){
                if (sioList.get(n).score == highScoreSio){
                    usernameSio.text = sioList.get(n).username
                    scoreSio.text = sioList.get(n).score.toString()
                    datetimeSio.text = sioList.get(n).datetime
                }
            }
        }
    }
}