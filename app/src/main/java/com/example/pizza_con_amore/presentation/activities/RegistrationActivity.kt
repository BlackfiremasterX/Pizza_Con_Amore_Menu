package com.example.pizza_con_amore.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pizza_con_amore.presentation.MainActivity
import com.example.pizza_con_amore.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }


    public fun login(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun end_register(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}