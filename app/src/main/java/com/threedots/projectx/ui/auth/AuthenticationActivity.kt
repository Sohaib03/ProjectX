package com.threedots.projectx.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.threedots.projectx.R
import com.threedots.projectx.data.repositories.UserRepository
import com.threedots.projectx.ui.home.HomeActivity

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        var users = UserRepository(this).getAllUsers()
        users.observe(this,  {
            if (it.isEmpty())  {
                supportFragmentManager.beginTransaction().add(
                    R.id.main_activity_fragment,
                    LoginFragment.newInstance(), "login").commit()
            }
            else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        })
        supportFragmentManager.beginTransaction().add(
            R.id.main_activity_fragment,
            LoginFragment.newInstance(), "login").commit()
        
    }
}