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
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    lateinit var recycle: RecyclerView
    lateinit var list:List<Task>
     lateinit var btnAdd:FloatingActionButton

    lateinit var myViewModel :MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle=findViewById(R.id.rvTask)

        btnAdd=findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }

        list= listOf()
        myViewModel= ViewModelProvider(this).get(MyViewModel::class.java)

        updatedrecycle()

        // new comment
    }
    fun updatedrecycle(){
        myViewModel.getTasks().observe(this,{
                tasks->
           // recycle.adapter = RecyclerView (this, tasks)
            recycle.layoutManager = LinearLayoutManager(this)
        })

    }

    fun update(task: Task) {

        val d = AlertDialog.Builder(this)
        lateinit var name: EditText
        lateinit var description: EditText
        lateinit var vv: View

        d.setCancelable(false)
        d.setPositiveButton("update") { _, _ ->
            task.taskName = name.text.toString()
            task.taskDescription = description.text.toString()

            myViewModel.updatesTasks(task.id,task.taskName,task.taskDescription)
        }
            .setNegativeButton("Cancel") { d, _ -> d.cancel() }


        val alert = d.create()
        alert.setTitle("Edit celebrity")
        vv=layoutInflater.inflate(R.layout.alert,null)
        alert.setView(vv)
        name= vv.findViewById(R.id.edTaskNameEdited)
        description=vv.findViewById(R.id.edTaskDescriptionedited)

        name.setText(task.taskName)
        description.setText(task.taskDescription)


        alert.show()

    }

    fun confirm(id:Int){

        var at= AlertDialog.Builder(this)
        at.setTitle("delete Note")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
            deleteitem(id)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        at.show()
    }

    private fun deleteitem(id:Int) {
        myViewModel.deleteTasks(id)
        Toast.makeText(applicationContext, "data successfully Deleted", Toast.LENGTH_SHORT)
            .show()

    }

    fun newpage(view: View) {
        startActivity(Intent(this,MainActivity2::class.java))
    }


}