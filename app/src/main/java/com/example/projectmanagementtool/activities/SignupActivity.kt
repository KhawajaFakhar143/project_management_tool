package com.example.projectmanagementtool.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projectmanagementtool.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : BaseActivity() {
    private var toolbarSignUp: Toolbar? = null
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

        btn_sign_up.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name: String = et_name.text.toString().trim { it <= ' ' }
        val email: String = et_email.text.toString().trim { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {

            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    // Hide the progress dialog
                    hideProgressDialog()

                    // If the registration is successfully done
                    if (task.isSuccessful) {

                        // Firebase registered user
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        // Registered Email
                        val registeredEmail = firebaseUser.email!!

                        Toast.makeText(
                            this@SignupActivity,
                            "$name you have successfully registered with email id $registeredEmail.",
                            Toast.LENGTH_SHORT
                        ).show()

                        /**
                         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                         * and send him to Intro Screen for Sign-In
                         */

                        /**
                         * Here the new user registered is automatically signed-in so we just sign-out the user from firebase
                         * and send him to Intro Screen for Sign-In
                         */
                        FirebaseAuth.getInstance().signOut()
                        // Finish the Sign-Up Screen
                        finish()
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


private fun validateForm(name: String, email: String, password: String): Boolean {
    return when {
        TextUtils.isEmpty(name) -> {
            showErrorSnackBar("Please enter name.")
            false
        }
        TextUtils.isEmpty(email) -> {
            showErrorSnackBar("Please enter email.")
            false
        }
        TextUtils.isEmpty(password) -> {
            showErrorSnackBar("Please enter password.")
            false
        }
        else -> {
            true
        }
    }
}

}