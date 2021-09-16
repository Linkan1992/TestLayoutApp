package com.example.testlayoutapp.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.testlayoutapp.R
import com.example.testlayoutapp.TTSActivity
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class LocalNotificationScheduler {

    private val NOTIFICATION_CHANNEL_ID = "tts_notification"
    private val TTS_NOTIFICATION_TEST = "tts_notification_test"
    private val NOTIFICATION_DESCRIPTION = "Used for TTS test notification"

    fun scheduleLocalNotification(applicationContext: Context, remoteMessage: RemoteMessage): Unit {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        if (notificationManager != null) {
         //   val intent: Intent = applicationContext.intent
            val notificationIntent: Intent = Intent(applicationContext, TTSActivity::class.java)
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            /*Timer().schedule(object : TimerTask() {
                override fun run() {*/
                    val id: String =
                        applicationContext.getString(R.string.notification_channel_id)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        var channel = notificationManager.getNotificationChannel(id)
                        if (channel == null) {
                            channel = NotificationChannel(
                                id,
                                applicationContext.getString(R.string.tts_notification_test),
                                NotificationManager.IMPORTANCE_LOW
                            )
                            channel.description = applicationContext
                                .getString(R.string.notification_channel_desc)
                            notificationManager.createNotificationChannel(channel)
                        }
                    }
                    val pi = PendingIntent.getActivity(
                        applicationContext,
                        0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    val builder: NotificationCompat.Builder =
                        NotificationCompat.Builder(applicationContext, id)
                            .setAutoCancel(true)
                            .setContentIntent(pi)
                            .setSmallIcon(R.drawable.ic_bell)
                            .setTicker(applicationContext.getString(R.string.test_ticker))
                            .setSubText(
                                applicationContext.getString(R.string.test_subtext)
                            )
                            .setContentTitle(
                                applicationContext.getString(R.string.test_content_title)
                            )
                            .setContentText(
                                applicationContext.getString(R.string.test_content_text)
                            )
                            .setContentInfo(
                                applicationContext.getString(R.string.test_content_info)
                            )
                            .setStyle(NotificationCompat.BigTextStyle()
                                .bigText(applicationContext.getString(R.string.test_content_text_large)))
                    notificationManager.notify(0, builder.build())
                /*}
            }, 5000)*/
        }
    }

    fun scheduleBackgroundNotification(applicationContext: Context, remoteMessage: RemoteMessage): Unit {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        if (notificationManager != null) {
            val notificationIntent: Intent = Intent(applicationContext, TTSActivity::class.java)
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            /*Timer().schedule(object : TimerTask() {
                override fun run() {*/
            val id: String = NOTIFICATION_CHANNEL_ID
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = notificationManager.getNotificationChannel(id)
                if (channel == null) {
                    channel = NotificationChannel(id,TTS_NOTIFICATION_TEST, NotificationManager.IMPORTANCE_HIGH)
                    channel.description = NOTIFICATION_DESCRIPTION
                    notificationManager.createNotificationChannel(channel)
                }
            }
            val pi = PendingIntent.getActivity(
                applicationContext, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, id)
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .setSmallIcon(R.drawable.ic_bell)
                    .setContentTitle(remoteMessage.data["test1"])
                    .setContentText(remoteMessage.data["test2"])
                    .setStyle(NotificationCompat.BigTextStyle().bigText( remoteMessage.data["test3"]))
            notificationManager.notify(0, builder.build())
            /*}
        }, 5000)*/
        }
    }

    fun scheduleForegroundNotification(applicationContext: Service, remoteMessage: RemoteMessage): Unit {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        if (notificationManager != null) {
            val notificationIntent: Intent = Intent(applicationContext, TTSActivity::class.java)
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val id: String = NOTIFICATION_CHANNEL_ID
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = notificationManager.getNotificationChannel(id)
                if (channel == null) {
                    channel = NotificationChannel(id,TTS_NOTIFICATION_TEST, NotificationManager.IMPORTANCE_HIGH)
                    channel.description = NOTIFICATION_DESCRIPTION
                    notificationManager.createNotificationChannel(channel)
                }
            }
            val pi = PendingIntent.getActivity(
                applicationContext, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, id)
                    .setAutoCancel(true)
                    .setContentIntent(pi)
                    .setSmallIcon(R.drawable.ic_bell)
                    .setContentTitle(remoteMessage.data["test1"])
                    .setContentText(remoteMessage.data["test2"])
                    .setStyle(NotificationCompat.BigTextStyle().bigText( remoteMessage.data["test3"]))

            applicationContext.startForeground(4, builder.build())
           // notificationManager.notify(0, builder.build())

        }
    }

    fun fakeNotification(applicationContext: Service){
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        if (notificationManager != null) {
            val id: String = NOTIFICATION_CHANNEL_ID
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var channel = notificationManager.getNotificationChannel(id)
                if (channel == null) {
                    channel = NotificationChannel(id,TTS_NOTIFICATION_TEST, NotificationManager.IMPORTANCE_LOW)
                    channel.description = NOTIFICATION_DESCRIPTION
                    notificationManager.createNotificationChannel(channel)
                }
            }

            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, id)
                    /*.setSmallIcon(R.drawable.ic_bell)
                    .setContentTitle("")
                    .setContentText("")*/
            applicationContext.startForeground(4, builder.build())
        }
    }

}