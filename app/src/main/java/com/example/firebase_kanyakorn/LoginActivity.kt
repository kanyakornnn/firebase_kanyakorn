package com.example.firebase_kanyakorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var signupText = findViewById<Button>(R.id.signupText)
        signupText.setOnClickListener() {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }
    }
}