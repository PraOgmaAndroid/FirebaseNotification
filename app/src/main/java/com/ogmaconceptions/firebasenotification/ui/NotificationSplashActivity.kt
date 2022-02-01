package com.ogmaconceptions.firebasenotification.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ogmaconceptions.firebasenotification.databinding.ActivitySplashBinding


class NotificationSplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        Log.e("CHECK", "${intent.getStringExtra("OPENACTIVITY")}")

        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                token = task.result.toString()

                Log.e("PRINT", token)

            })

    }
}