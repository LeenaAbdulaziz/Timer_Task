package com.example.timer_task

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import kotlinx.android.synthetic.main.card_task.view.*

class MainActivity : AppCompatActivity() {

    lateinit var recycle: RecyclerView
    lateinit var list:List<Task>
    lateinit var totalTime:TextView

    val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle = findViewById(R.id.rvTask)

        totalTime=findViewById(R.id.tvTotalTime)

        updatedrecycle()
    }

    fun updatedrecycle(){
        myViewModel.getTasks().observe(this,{
            updateRV(it)
            list=it
            calculate()
        })
    }
    fun updateRV(list:List<Task>) {
        recycle.adapter = recyclerView( list,this)
        recycle.layoutManager = LinearLayoutManager(this)
    }

    fun newpage(view: View) {
        startActivity(Intent(this,MainActivity2::class.java))
    }


   fun calculate(){

       var sum=0
       for(task in list){
           sum+=task.timer
       }
       var hours = sum / 3600;
       var minutes =( sum % 3600) / 60;
       var seconds = sum % 60;
       totalTime.text = "Total Time: "+String.format("%01d:%02d:%02d", hours, minutes, seconds);


   }


}