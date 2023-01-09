package com.gandalf.utsmpr

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gandalf.utsmpr.fragment.DivisorFragment
import com.gandalf.utsmpr.fragment.OperationMathFragment
import com.gandalf.utsmpr.fragment.SortItOutFragment

class GameActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    lateinit var timerProgressBar: ProgressBar
    var maxTimeInMillis = 5000L
    val minTimeInMillis = 0L
    val intervalInMillis = 1L
    lateinit var timer: CountDownTimer

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        timerProgressBar = findViewById(R.id.timerProgressBar)
        timerProgressBar.max = (maxTimeInMillis/1000).toInt()
        timerProgressBar.min = (minTimeInMillis/1000).toInt()

        timer = object : CountDownTimer(maxTimeInMillis, intervalInMillis)
        {
            override fun onTick(millisUntilFinished: Long) {
                timerProgressBar.progress = (millisUntilFinished/1000.0).toInt()

            }

            override fun onFinish() {
                Toast.makeText(this@GameActivity,"Waktu Habis", Toast.LENGTH_SHORT).show()

                Handler().postDelayed(
                    {
                        finish()
                    },500
                )
            }
        }

        val intent = intent
        val judul = intent.getStringExtra("judul")
        val id = intent.getIntExtra("id", 0)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(judul)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        toolbar.setSubtitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setNavigationOnClickListener{
            finish()
            timer.cancel()
        }

        val DivisorFragment = DivisorFragment()
        val OperationMathFragment = OperationMathFragment()
        val SortItOutFragment = SortItOutFragment()

        if (id == 1){
            makeCurrentFragment(DivisorFragment)
            timer.start()
        }
        else if(id == 2){
            makeCurrentFragment(OperationMathFragment)
            timer.start()
        }
        else{
            makeCurrentFragment(SortItOutFragment)
            timer.start()
        }

    }

    private fun makeCurrentFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        timer.cancel()
    }
}