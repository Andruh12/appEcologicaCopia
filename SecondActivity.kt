package com.example.app1

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textOlvidasteContrasena = findViewById<TextView>(R.id.textOlvidasteContrasena)
        textOlvidasteContrasena.setOnClickListener {

            val intent = Intent(this, FourActivity::class.java)
            startActivity(intent)
        }
    }
}
