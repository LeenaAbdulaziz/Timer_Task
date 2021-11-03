package com.example.timer_task

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp_room.data2.Task

class MainActivity2 : AppCompatActivity() {
lateinit var taskName:EditText
lateinit var taskDescription:EditText
lateinit var savebtn:Button
lateinit var viewbtn:Button
    lateinit var myViewModel :MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
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
            if(s1.isNotEmpty()&&s1.isNotEmpty()) {
                myViewModel.addTasks(s1,s2)
                taskName.text.clear()
                taskDescription.text.clear()

                Toast.makeText(applicationContext, "data successfully added", Toast.LENGTH_SHORT)
                    .show()



            }
            else{
                Toast.makeText(applicationContext,"please add note first", Toast.LENGTH_SHORT).show()
            }
        }
        viewbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }


}