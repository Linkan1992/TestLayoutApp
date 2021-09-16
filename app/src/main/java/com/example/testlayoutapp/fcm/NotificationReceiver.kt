package com.example.testlayoutapp.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.RemoteMessage

class NotificationReceiver : BroadcastReceiver() {

    companion object{
        private const val TAG = "NotificationReceiver"

        const val FCMNotification = "com.example.testlayoutapp.fcm.notification"
    }

    override fun onReceive(context: Context, intent: Intent?) {

        Log.d(TAG, "From: ${intent?.getParcelableExtra<RemoteMessage>(MyFirebaseMessagingService.KEY_REMOTE_MESSAGE)?.data}")

        intent?.let {
            when(it.action){
                FCMNotification -> {
                    it.getParcelableExtra<RemoteMessage>(MyFirebaseMessagingService.KEY_REMOTE_MESSAGE)?.run {
                        LocalNotificationScheduler().scheduleBackgroundNotification(context, this)
                    }
                }
                else -> {}
            }
        }

    }


}