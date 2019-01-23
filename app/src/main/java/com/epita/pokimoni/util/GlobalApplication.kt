package com.epita.pokimoni.util

import android.app.Application
import android.content.Context

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        workerThread = WorkerThread("workerThread")
    }

    companion object {

        var workerThread: WorkerThread? = null
            private set
        var appContext: Context? = null
            private set
    }
}
