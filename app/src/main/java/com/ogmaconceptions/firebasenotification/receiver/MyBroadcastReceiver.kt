package com.ogmaconceptions.firebasenotification.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ogmaconceptions.firebasenotification.gcm.FCMMessaging
import com.ogmaconceptions.firebasenotification.ui.DialogActivity

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val intentValue = p1?.action
        //Log.e("PRINT","$intentValue")
        intentValue?.let {
            if (it == FCMMessaging.KEY_CLICK) {
                p0?.let { context ->
                    Toast.makeText(context, "BROADCASTED", Toast.LENGTH_SHORT).show()
                    Intent(context, DialogActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        putExtra("OPENACTIVITY", "1").also { intent ->
                            context.startActivity(intent)
                        }
                    }
                }

            }
        }
    }
}