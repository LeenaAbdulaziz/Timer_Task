package com.example.timer_task.Pages

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp_room.data2.Task
import com.example.timer_task.MyViewModel
import com.example.timer_task.R

class AddTaskPage : AppCompatActivity()
{
    lateinit var taskName:EditText
    lateinit var taskDescription:EditText
    lateinit var savebtn:Button
    lateinit var viewbtn:Button
    lateinit var myViewModel : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        taskName=findViewById(R.id.edNameTask)
        taskDescription=findViewById(R.id.edDescription)
        savebtn=findViewById(R.id.btnSave)
        viewbtn=findViewById(R.id.btnView)

        myViewModel= ViewModelProvider(this).get(MyViewModel::class.java)

        savebtn.setOnClickListener {
            val s1=taskName.text.toString()
            val s2=taskDescription.text.toString()
            if(s1.isNotEmpty()&&s2.isNotEmpty())
            {
                val task=Task(0,s1,s2,0,false)
                myViewModel.addTasks(task)
                taskName.text.clear()
                taskDescription.text.clear()
                Toast.makeText(applicationContext, "data successfully added", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {
                Toast.makeText(applicationContext,"All fields are required!", Toast.LENGTH_SHORT).show()
            }
        }
        viewbtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }


}