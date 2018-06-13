package com.example.nikhil.ssoclient

import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ResultInterface {

    private lateinit var receiver: SSOClientReceiver
    private val filter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = SSOClientReceiver()
        SSOClientReceiver.setInterface(this)

        filter.addAction("android.intent.action.CLIENT_LOGIN_STATUS")

        login.setOnClickListener {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = "android.intent.action.SSOLogin"
            sendBroadcast(intent)
        }

        val statusIntent = Intent()
        statusIntent.action = "android.intent.action.LoginStatus"
        sendBroadcast(statusIntent)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun statusReceived(loggedIn: Boolean, intent: Intent) {
        if (loggedIn){
            val uName = intent.getStringExtra("username")
            login.visibility = View.GONE
            Log.d("MainActivity", "client is logged in")
            loginStatus.text = "Already logged in with username: $uName"
        } else {
            loginStatus.text = "Not logged in"
            Log.d("MainActivity", "client is not logged in")
            login.visibility = View.VISIBLE
        }
    }
}
