package com.example.timer_task

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.timer_task.Pages.MainActivity

class startUp : AppCompatActivity()
{
    private lateinit var btnStart :Button
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)

        btnStart = findViewById(R.id.btnStart)
        btnStart.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}