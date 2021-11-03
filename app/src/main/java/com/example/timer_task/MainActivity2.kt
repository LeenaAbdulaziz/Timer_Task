package com.example.timer_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity2 : AppCompatActivity() {
    lateinit var edNameTask: EditText
    lateinit var edDescription: EditText
    lateinit var btnSave: Button
    lateinit var btnView: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        edNameTask=findViewById(R.id.edNameTask)
        edDescription=findViewById(R.id.edDescription)
        btnSave=findViewById(R.id.btnSave)
        btnView=findViewById(R.id.btnView)
    }
}