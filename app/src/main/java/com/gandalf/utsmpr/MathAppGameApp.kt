package com.gandalf.utsmpr

import android.app.Application

class MathAppGameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MathAppGameRepo.initialize(this)
    }
}