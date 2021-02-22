package com.threedots.projectx.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.threedots.projectx.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportFragmentManager.beginTransaction().add(
            R.id.home_frame_layout,
            HomeFragment(),
            "home"
        ).commit()
    }
}