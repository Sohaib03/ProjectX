package com.threedots.projectx.ui.auth

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.threedots.projectx.data.repositories.UserRepository
import com.threedots.projectx.util.validEmail

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    var username : String? = null
    var email : String? = null
    var password : String? = null
    var confirmPassword : String? = null
    var authListener : AuthListener? = null

    fun onRegisterButtonClick(view : View) {
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty() || username.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {
            // Display error
            authListener?.onFailure("No field can be left empty")
            return
        }
        if (!validEmail(email!!)) {
            authListener?.onFailure("Invalid Email Format")
            return
        }
        if (!password.equals(confirmPassword)) {
            authListener?.onFailure("Passwords do not match")
            return
        }
        val loginResponse = UserRepository(getApplication<Application>().applicationContext).userRegister(username!!, email!!, password!!)
        authListener?.onSuccess(loginResponse)
        // Success
    }
}
