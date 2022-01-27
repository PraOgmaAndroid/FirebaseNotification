package com.ogmaconceptions.firebasenotification.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ogmaconceptions.firebasenotification.databinding.ActivitySplashBinding

class NotificationSplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val flag: String? = intent.getStringExtra("flag")
        val title: String? = intent.extras?.getString("title")
        val message: String? = intent.extras?.getString("message")
        val path: String? = intent.extras?.getString("path")

        Log.e("PRINT","Intent Flag $flag $title $message $path")
        flag?.let {
            if(it == "1"){
                Intent(this@NotificationSplashActivity,PictureActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("title",title)
                    putExtra("message",message)
                    putExtra("path",path)
                }.also { intentActivity ->
                    startActivity(intentActivity)
                }
            }else{
                Log.e("PRINT","$flag")
                Intent(this@NotificationSplashActivity,MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("title",title)
                    putExtra("message",message)
                }.also {intentActivity2 ->
                    startActivity(intentActivity2)
                }
            }
        }


    }
}