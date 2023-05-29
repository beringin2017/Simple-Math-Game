package com.gandalf.simpleMathGame

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gandalf.simpleMathGame.data.HighScore
import com.gandalf.simpleMathGame.fragment.DivisorFragment
import com.gandalf.simpleMathGame.fragment.OperationMathFragment
import com.gandalf.simpleMathGame.fragment.SortItOutFragment
import java.text.SimpleDateFormat
import java.util.*

class GameActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    lateinit var timerProgressBar: ProgressBar
    var maxTimeInMillis = 30000L
    val minTimeInMillis = 0L
    val intervalInMillis = 1L
    lateinit var timer: CountDownTimer
    private lateinit var dialog: Dialog
    private lateinit var username : String
    var highScore = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        dialog = Dialog(this)
        dialog.setContentView(R.layout.skor_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val skor = dialog.findViewById<TextView>(R.id.skor)
        val kembali = dialog.findViewById<Button>(R.id.kembali)
        val playagain = dialog.findViewById<Button>(R.id.playagain)
        val highscore = dialog.findViewById<Button>(R.id.highscore)

        val intent = intent
        val judul = intent.getStringExtra("judul")
        val id = intent.getIntExtra("id", 0)

        username = sharedPreferences.getString("username","").toString()

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
                highScore = sharedPreferences.getInt("skor",0)
                skor.text = highScore.toString()
                dialog.show()
                kembali.setOnClickListener(){
                    if (id == 1){
                        insertDataDivisor()
                    }
                    else if(id == 2){
                        insertDataOprmath()
                    }
                    else{
                        insertDataSio()
                    }
                    dialog.dismiss()
                    finish()
                }
                playagain.setOnClickListener(){
                    if (id == 1){
                        insertDataDivisor()
                    }
                    else if(id == 2){
                        insertDataOprmath()
                    }
                    else{
                        insertDataSio()
                    }
                    dialog.dismiss()
                    recreate()
                }
                highscore.setOnClickListener(){
                    if (id == 1){
                        insertDataDivisor()
                    }
                    else if(id == 2){
                        insertDataOprmath()
                    }
                    else{
                        insertDataSio()
                    }
                    dialog.dismiss()
                    finish()
                    val intent = Intent(applicationContext, HighScoreDisplay::class.java)
                    intent.putExtra("judul", "High Score")
                    intent.putExtra("id", 4)
                    startActivity(intent)
                }
            }
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(judul+" - $username")
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

    private fun insertDataDivisor(){
        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time)
        highScore = sharedPreferences.getInt("skor",0)
        var db = MyDBHelper(this)
        val hs = HighScore(username,highScore,dateTime)
        val updateScore = db.insertDataDivisor(hs)
        if (updateScore > -1){
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertDataOprmath(){
        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time)
        highScore = sharedPreferences.getInt("skor",0)
        var db = MyDBHelper(this)
        val hs = HighScore(username,highScore,dateTime)
        val updateScore = db.insertDataOprmath(hs)
        if (updateScore > -1){
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertDataSio(){
        val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time)
        highScore = sharedPreferences.getInt("skor",0)
        var db = MyDBHelper(this)
        val hs = HighScore(username,highScore,dateTime)
        val updateScore = db.insertDataSio(hs)
        if (updateScore > -1){
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
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