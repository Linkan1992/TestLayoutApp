package com.example.testlayoutapp.fcm

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.RemoteMessage
import java.util.*

@SuppressLint("OverrideAbstract")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
class MyNotificationListener : NotificationListenerService() {

    private var TAG = "MyNotificationListener"

    private lateinit var textToSpeechEngine: TextToSpeech

    override fun onCreate() {
        // Pass in context and the listener.
        textToSpeechEngine = TextToSpeech(
            this,
            TextToSpeech.OnInitListener { status ->
                textToSpeechEngine.language = Locale.UK

            }, "com.google.android.tts"
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.also { intentData ->
            val remoteMessage = intentData.getParcelableExtra<RemoteMessage>(MyFirebaseMessagingService.KEY_REMOTE_MESSAGE)
            if (remoteMessage != null )LocalNotificationScheduler().scheduleForegroundNotification(this, remoteMessage)
        }
        return START_STICKY
    }


    override fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.i(TAG, "ID:" + sbn.id)
        Log.i(TAG, "Posted by:" + sbn.packageName)
        Log.i(TAG, "tickerText:" + sbn.notification.tickerText)
        for (key in sbn.notification.extras.keySet()) {
            Log.i(TAG, key + "=" + sbn.notification.extras[key].toString())
        }

        Handler(Looper.getMainLooper()).post {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Call Lollipop+ function
                if (::textToSpeechEngine.isInitialized) textToSpeechEngine.speak("Posted by:" + sbn.packageName,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "tts1"
                )
            } else {
                // Call Legacy function
                if (::textToSpeechEngine.isInitialized) textToSpeechEngine.speak("Posted by:" + sbn.packageName,
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }



}