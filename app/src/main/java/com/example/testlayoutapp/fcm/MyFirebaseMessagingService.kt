package com.example.testlayoutapp.fcm


import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MyFirebaseMsgService"
        const val KEY_DATA = "data"
        const val KEY_NOTIFICATION = "notification"
        const val KEY_REMOTE_MESSAGE = "remote_message"
        /**
         * Method to get FCM Token
         */
        public fun doSendNotification(){

            //get user notification token provided by firebase
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("token_failed", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val notificationToken = task.result
                Log.d(TAG, "Refreshed token: $notificationToken")

                //store the user name
            })

        }
    }

    //this is called when a message is received
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        //check messages
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload, you can get the payload here and add as an intent to your activity
        remoteMessage.data.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            //get the data
           // LocalNotificationScheduler().scheduleBackgroundNotification(this, remoteMessage)

            // broadcastNotification(remoteMessage)

            sendNotificationToService(remoteMessage)

        }

        // Check if message contains a notification payload, send notification
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            //sendNotification(it.body!!)
          //  LocalNotificationScheduler().scheduleLocalNotification(applicationContext, remoteMessage)
        }




    }

    override fun onNewToken(token: String) {

        Log.d("rfst", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)

    }

    private fun sendRegistrationToServer(token: String?) {
        //you can send the updated value of the token to your server here
    }

    private fun broadcastNotification(remoteMessage: RemoteMessage){
        /**
         * Explicit broadcast for Build.VERSION_CODES > Android 8.0 (API level 26)
         */
        val broadcastIntent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.action = NotificationReceiver.FCMNotification
        broadcastIntent.putExtra(KEY_REMOTE_MESSAGE, remoteMessage)
        //  broadcastIntent.putExtra(KEY_DATA, remoteMessage.data)
        //  broadcastIntent.putExtra(KEY_NOTIFICATION, remoteMessage.notification)
        sendBroadcast(broadcastIntent)
        //  LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }


    private fun sendNotificationToService(remoteMessage: RemoteMessage){
        val notificationListenerIntent = Intent(this, MyNotificationListener::class.java)
        notificationListenerIntent.putExtra(KEY_REMOTE_MESSAGE, remoteMessage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
           // ContextCompat.startForegroundService(this, notificationListenerIntent)
            startForegroundService(notificationListenerIntent)
         else
            startService(notificationListenerIntent)

    }

}