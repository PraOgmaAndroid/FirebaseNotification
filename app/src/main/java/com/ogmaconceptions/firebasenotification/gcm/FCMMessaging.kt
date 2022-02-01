package com.ogmaconceptions.firebasenotification.gcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ogmaconceptions.firebasenotification.R
import com.ogmaconceptions.firebasenotification.receiver.MyBroadcastReceiver
import java.util.*

class FCMMessaging : FirebaseMessagingService() {

    companion object {
        val KEY_CLICK = "click_to_open"
    }

    private lateinit var title: String
    private lateinit var message: String
    private lateinit var imageUrl: String
    private lateinit var click_action: String
    private lateinit var pendingIntent: PendingIntent
    private val notificationChannelID: String = "10001"
    private val br: BroadcastReceiver = MyBroadcastReceiver()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("FCMTOKEN", token)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        LocalBroadcastManager.getInstance(this).registerReceiver(br, IntentFilter().apply {
            addAction(
                KEY_CLICK
            )
        })

        Log.e("PRINT", "${p0.data}")

        /*p0.notification?.let {
            Log.e("TAG", "Notification Title: ${it.title}")
            Log.e("TAG", "Notification Body: ${it.body}")

            *//*val intent = Intent(this, Splash::class.java)
            it.title?.let { it1 ->
                it.body?.let { it2 ->
                    sendNotification(it1, it2, intent)
                }
            }*//*
        }*/

        if (p0.data.isNotEmpty()) {


            LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
                action = KEY_CLICK
            })

            title = p0.data["title"].toString()
            message = p0.data["message"].toString()
            imageUrl = p0.data["path"].toString()
            click_action =
                "com.ogmaconceptions.firebasenotification_${p0.data["click_action"].toString()}"

            Log.e("PRINT", click_action)

            sendnotificationWithImage(title, message, imageUrl)
        }

    }

    private fun sendnotificationWithImage(title: String, message: String, imageUrl: String) {

        val notificationId = Random().nextInt(60000)

        Intent(click_action).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("title", title)
            putExtra("message", message)
            putExtra("path", imageUrl)
        }.also {
            pendingIntent = PendingIntent.getActivity(
                this, 0, it,0)
        }

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,notificationChannelID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                notificationChannelID,
                resources.getString(R.string.app_name),
                importance
            )
            notificationBuilder.setChannelId(notificationChannelID)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(
            notificationId /* ID of notification */,
            notificationBuilder.build()
        )

        LocalBroadcastManager.getInstance(this).unregisterReceiver(br)

    }


}