package com.threedots.projectx.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun isConnected() : Boolean
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message :String)
}