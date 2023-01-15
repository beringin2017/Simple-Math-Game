package com.gandalf.utsmpr

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var dialog:Dialog

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

        dialog = Dialog(this)
        dialog.setContentView(R.layout.username_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val back = dialog.findViewById<Button>(R.id.back)
        val start = dialog.findViewById<Button>(R.id.start)
        val username = dialog.findViewById<EditText>(R.id.username)

        val divisor = findViewById<LinearLayout>(R.id.divisor)
        val oprmath = findViewById<LinearLayout>(R.id.oprmath)
        val sio = findViewById<LinearLayout>(R.id.sio)
        val hs = findViewById<LinearLayout>(R.id.hsButton)
        val name = findViewById<TextView>(R.id.name)

        var db = MyDBHelper(this)

        divisor.setOnClickListener {
            dialog.show()
            back.setOnClickListener(){
                dialog.dismiss()
            }
            start.setOnClickListener(){
                val sUsername = username.text.toString()
                editor.putString("username",sUsername)
                editor.apply()
                username.text.clear()
                name.text = sUsername
                dialog.dismiss()
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("judul", "Divisor")
                intent.putExtra("id", 1)
                startActivity(intent)
            }
        }

        oprmath.setOnClickListener {
            dialog.show()
            back.setOnClickListener(){
                dialog.dismiss()
            }
            start.setOnClickListener() {
                val sUsername = username.text.toString()
                editor.putString("username",sUsername)
                editor.apply()
                username.text.clear()
                dialog.dismiss()
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("judul", "Operation Math")
                intent.putExtra("id", 2)
                startActivity(intent)
            }
        }

        sio.setOnClickListener {
            dialog.show()
            back.setOnClickListener(){
                dialog.dismiss()
            }
            start.setOnClickListener() {
                val sUsername = username.text.toString()
                editor.putString("username",sUsername)
                editor.apply()
                username.text.clear()
                dialog.dismiss()
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("judul", "Sort It Out")
                intent.putExtra("id", 3)
                startActivity(intent)
            }
        }

        hs.setOnClickListener{
            val intent = Intent(this, HighScoreDisplay::class.java)
            intent.putExtra("judul", "High Score")
            intent.putExtra("id", 4)
            startActivity(intent)
        }

    }
}