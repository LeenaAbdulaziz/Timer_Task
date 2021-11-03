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




    lateinit var myViewModel :MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.rvTask)

        list = listOf()
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        updatedrecycle()

    }


    fun updatedrecycle(){
        myViewModel.getTasks().observe(this,{
            //tasks->
            updateRV(it)
            // u
            //  recycle.adapter = RecyclerView (this, tasks)
            // recycle.layoutManager = LinearLayoutManager(this)
        })

    }
    fun updateRV(list:List<Task>) {
        recycle.adapter = recyclerView( list,this)
        recycle.layoutManager = LinearLayoutManager(this)
    }

    fun update(task: Task) {

            myViewModel.updatesTasks(task.id,task.taskName,task.taskDescription)
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