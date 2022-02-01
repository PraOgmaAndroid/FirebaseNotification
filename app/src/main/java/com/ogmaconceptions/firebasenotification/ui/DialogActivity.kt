package com.ogmaconceptions.firebasenotification.ui

import android.content.BroadcastReceiver
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ogmaconceptions.firebasenotification.databinding.ActivityDialogBinding
import com.ogmaconceptions.firebasenotification.receiver.MyBroadcastReceiver

class DialogActivity : AppCompatActivity() {
    private val br: BroadcastReceiver = MyBroadcastReceiver()
    private lateinit var dialogBinding: ActivityDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        dialogBinding = ActivityDialogBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(dialogBinding.root)

        Log.e("CHECK", "${intent.getStringExtra("OPENACTIVITY")}")

        this.setFinishOnTouchOutside(true)

        dialogBinding.btnOK.setOnClickListener {
            finish()
        }
    }


}