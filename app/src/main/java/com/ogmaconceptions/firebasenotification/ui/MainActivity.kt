package com.ogmaconceptions.firebasenotification.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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