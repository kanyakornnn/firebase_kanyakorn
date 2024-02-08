package com.example.firebase_kanyakorn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivitytest02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activitytest02)
        var Lbt = findViewById<Button>(R.id.Lbt)
        Lbt.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        var Cbt = findViewById<Button>(R.id.Cbt)
        Cbt.setOnClickListener() {
            val intent1 = Intent(this, CalcuActivity::class.java)
            startActivity(intent1)
        }

    }
}