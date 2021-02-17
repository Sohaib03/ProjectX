package com.threedots.projectx.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.threedots.projectx.data.repositories.UserRepository

class AuthViewModel : ViewModel() {
    var email : String? = null
    var password : String? = null
    var authListener : AuthListener? = null

    fun onLoginButtonClick(view : View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            // Display error
            authListener?.onFailure("Invalid Email / Pass")
            return
        }
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
        // Success
    }
}