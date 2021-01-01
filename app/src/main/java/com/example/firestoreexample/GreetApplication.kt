package com.example.firestoreexample

 import android.app.Application
import android.content.Context
import timber.log.Timber

class GreetApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        context = applicationContext
        Timber.plant(Timber.DebugTree())
    }

    companion object{
        lateinit var context: Context
    }
}