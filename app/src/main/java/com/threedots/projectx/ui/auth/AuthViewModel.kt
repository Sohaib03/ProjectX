package com.threedots.projectx.ui.auth

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.threedots.projectx.data.repositories.UserRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view : View) {
        if (authListener?.isConnected() == false) {
            authListener?.onFailure("Not Connected to Internet")
            return
        }
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // Display error
            authListener?.onFailure("Invalid Email / Pass")
            return
        }
        val loginResponse = UserRepository(getApplication<Application>().applicationContext).userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
        // Success
    }
}