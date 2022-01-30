package com.ogmaconceptions.firebasenotification.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ogmaconceptions.firebasenotification.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    private lateinit var pictureBinding: ActivityPictureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pictureBinding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(pictureBinding.root)

        with(intent){
            val title: String? = getStringExtra("title")

            title?.let {
                pictureBinding.tvTitle.text = it
            }

            val message: String? = getStringExtra("message")

            message?.let {
                pictureBinding.tvMessage.text = it
            }

            val imgPath: String? = getStringExtra("path")

            Log.e("PRINTPICTURE", getStringExtra("path").toString())

            imgPath?.let {
                Glide.with(this@PictureActivity).load(it).into(pictureBinding.shapableImg)
            }

        }


    }
}