package com.gandalf.simpleMathGame

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.gandalf.simpleMathGame.data.HighScore

class MyDBHelper(var context : Context) : SQLiteOpenHelper(context,"highscore.db",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE divisor(username TEXT PRIMARY KEY, score INTEGER,datetime TEXT)")
        db?.execSQL("CREATE TABLE oprmath(username TEXT PRIMARY KEY, score INTEGER,datetime TEXT)")
        db?.execSQL("CREATE TABLE sio(username TEXT PRIMARY KEY, score INTEGER,datetime TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDataDivisor(highScore: HighScore):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username",highScore.username)
        cv.put("score",highScore.score)
        cv.put("datetime",highScore.datetime)
        val success = db.insert("divisor",null,cv)
        return success
    }

    fun insertDataOprmath(highScore: HighScore):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username",highScore.username)
        cv.put("score",highScore.score)
        cv.put("datetime",highScore.datetime)
        val success = db.insert("oprmath",null,cv)
        return success
    }

    fun insertDataSio(highScore: HighScore):Long{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username",highScore.username)
        cv.put("score",highScore.score)
        cv.put("datetime",highScore.datetime)
        val success = db.insert("sio",null,cv)
        return success
    }

    @SuppressLint("Range")
    fun getDataDivisor() : ArrayList<HighScore>{
        val hsList: ArrayList<HighScore> = ArrayList()
        val selectQuery = "SELECT * FROM divisor"
        val db = this.readableDatabase
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return hsList
        }
        var username : String
        var score : Int
        var datetime : String

        if (cursor.moveToNext()){
            do {
                username = cursor.getString(cursor.getColumnIndex("username"))
                score = cursor.getInt(cursor.getColumnIndex("score"))
                datetime = cursor.getString(cursor.getColumnIndex("datetime"))

                val highScore = HighScore(username,score,datetime)
                hsList.add(highScore)
            }while (cursor.moveToNext())
        }
        return hsList
    }

    @SuppressLint("Range")
    fun getDataOprmath() : ArrayList<HighScore>{
        val hsList: ArrayList<HighScore> = ArrayList()
        val selectQuery = "SELECT * FROM oprmath"
        val db = this.readableDatabase
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return hsList
        }
        var username : String
        var score : Int
        var datetime : String

        if (cursor.moveToNext()){
            do {
                username = cursor.getString(cursor.getColumnIndex("username"))
                score = cursor.getInt(cursor.getColumnIndex("score"))
                datetime = cursor.getString(cursor.getColumnIndex("datetime"))

                val highScore = HighScore(username,score,datetime)
                hsList.add(highScore)
            }while (cursor.moveToNext())
        }
        return hsList
    }

    @SuppressLint("Range")
    fun getDataSio() : ArrayList<HighScore>{
        val hsList: ArrayList<HighScore> = ArrayList()
        val selectQuery = "SELECT * FROM sio"
        val db = this.readableDatabase
        val cursor : Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }
        catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return hsList
        }
        var username : String
        var score : Int
        var datetime : String

        if (cursor.moveToNext()){
            do {
                username = cursor.getString(cursor.getColumnIndex("username"))
                score = cursor.getInt(cursor.getColumnIndex("score"))
                datetime = cursor.getString(cursor.getColumnIndex("datetime"))

                val highScore = HighScore(username,score,datetime)
                hsList.add(highScore)
            }while (cursor.moveToNext())
        }
        return hsList
    }

}