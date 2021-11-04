package com.example.timer_task

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task

class MainActivity : AppCompatActivity() {

    lateinit var recycle: RecyclerView
    lateinit var list:List<Task>

    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle = findViewById(R.id.rvTask)
        list = listOf()
        updatedrecycle()
    }

    fun updatedrecycle(){
        myViewModel.getTasks().observe(this,{
            updateRV(it)
        })
    }
    fun updateRV(list:List<Task>) {
        recycle.adapter = recyclerView( list,this)
        recycle.layoutManager = LinearLayoutManager(this)
    }

    fun newpage(view: View) {
        startActivity(Intent(this,MainActivity2::class.java))
    }


}