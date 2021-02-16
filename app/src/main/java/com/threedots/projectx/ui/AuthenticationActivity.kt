package com.threedots.projectx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.threedots.projectx.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(
            R.id.main_activity_fragment,
            LoginFragment.newInstance(), "login").commit()
        
    }
}