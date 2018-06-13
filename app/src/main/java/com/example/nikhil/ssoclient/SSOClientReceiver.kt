package com.example.nikhil.ssoclient

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SSOClientReceiver: BroadcastReceiver() {

    companion object {
        lateinit var resultInterface: ResultInterface
        fun setInterface(resultInterface: ResultInterface) {
            this.resultInterface = resultInterface
        }
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            val action = intent.action
            var loggedIn = false
            Log.d("ClientReceiver", "response received to client")
            if (action == "android.intent.action.CLIENT_LOGIN_STATUS") {
                loggedIn = intent.getBooleanExtra("loginStatus", false)
            }
            resultInterface.statusReceived(loggedIn, intent)
        }
    }
}