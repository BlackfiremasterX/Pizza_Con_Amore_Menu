package com.example.pizza_con_amore.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pizza_con_amore.MainActivity
import com.example.pizza_con_amore.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun register(view: View) {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }

    fun sign_in(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}