package com.example.projectmanagementtool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import com.example.projectmanagementtool.R

class SignupActivity : AppCompatActivity() {
    private var toolbarSignUp : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        toolbarSignUp = findViewById(R.id.toolbar_sign_up_activity)
        setupActionBar()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
    private fun setupActionBar() {

        setSupportActionBar(toolbarSignUp)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbarSignUp?.setNavigationOnClickListener { onBackPressed() }
    }



}