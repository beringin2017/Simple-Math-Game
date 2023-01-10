package com.gandalf.utsmpr

import android.content.Context
import androidx.room.Room

class MathAppGameRepo private constructor(applicationContext: Context) {
    companion object{
        var DBINSTANCE : MathAppGameRepo ?= null

        lateinit var  db : MathAppGameDatabase

        fun initialize(applicationContext: Context)
        {
            if(DBINSTANCE == null) {
                System.out.println("InitializedDB")
                DBINSTANCE = MathAppGameRepo(applicationContext)

                db = Room.databaseBuilder(
                    applicationContext,MathAppGameDatabase::class.java,
                    "highscore"
                ).fallbackToDestructiveMigration().build()
            }
        }
    }



}