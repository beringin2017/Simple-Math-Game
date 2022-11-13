package com.gandalf.utsmpr

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("Math Game")
        toolbar.setSubtitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))

        val divisor = findViewById<LinearLayout>(R.id.divisor)
        val oprmath = findViewById<LinearLayout>(R.id.oprmath)
        val sio = findViewById<LinearLayout>(R.id.sio)

        divisor.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("judul", "Divisor")
            intent.putExtra("id", 1)
            startActivity(intent)
        }

        oprmath.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("judul", "Operation Math")
            intent.putExtra("id", 2)
            startActivity(intent)
        }

        sio.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            intent.putExtra("judul", "Sort It Out")
            intent.putExtra("id", 3)
            startActivity(intent)
        }

    }
}