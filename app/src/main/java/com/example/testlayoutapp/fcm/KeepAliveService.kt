package com.example.testlayoutapp.fcm

import android.app.Service
import android.content.Intent
import android.os.IBinder

class KeepAliveService : Service(){

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}