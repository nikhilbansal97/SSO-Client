package com.example.nikhil.ssoclient

import android.content.Intent

interface ResultInterface {
    fun statusReceived(loggedIn: Boolean, intent: Intent)
}