package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val welcome : Button = findViewById(R.id.button)
//        welcome.setOnClickListener{
//            val intent = Intent(this, toDoList::class.java)
//            startActivity(intent)
//        }


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MainActivity, toDoList::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}