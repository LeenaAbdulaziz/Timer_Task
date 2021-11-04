package com.example.timer_task.Pages

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import com.example.timer_task.MyViewModel
import com.example.timer_task.R
import com.example.timer_task.Adapter.recyclerView

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
            recycle.adapter = recyclerView( it,this)
            recycle.layoutManager = LinearLayoutManager(this)
            list=it
            calculate()
        })
    }

    fun newpage(view: View) {
        startActivity(Intent(this, AddTaskPage::class.java))
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