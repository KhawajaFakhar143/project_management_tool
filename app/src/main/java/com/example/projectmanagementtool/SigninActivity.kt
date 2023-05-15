package com.example.projectmanagementtool

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class SigninActivity : AppCompatActivity() {
    private var toolbarSignIn : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        toolbarSignIn = findViewById(R.id.toolbar_sign_in_activity)
        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbarSignIn)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbarSignIn?.setNavigationOnClickListener { onBackPressed() }
    }
}