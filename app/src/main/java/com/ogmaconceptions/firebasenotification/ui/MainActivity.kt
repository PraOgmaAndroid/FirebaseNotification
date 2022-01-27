package com.ogmaconceptions.firebasenotification.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ogmaconceptions.firebasenotification.R
import com.ogmaconceptions.firebasenotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        with(intent){
            val title: String? = extras?.getString("title")

            title?.let {
                mainBinding.tvTitle.text = it
            }

            val message: String? = extras?.getString("message")

            message?.let {
                mainBinding.tvMessage.text = it
            }

        }

    }
}