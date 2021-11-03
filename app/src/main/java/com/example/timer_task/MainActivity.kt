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
    lateinit var add:Button
    var isStart=false
    var tempTime=0
    var current_second=0
    lateinit var tvTaskTime: TextView
    lateinit var itemClick:LinearLayout


    lateinit var myViewModel :MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycle=findViewById(R.id.rvTask)
        tvTaskTime=findViewById(R.id.tvTaskTime)
        itemClick=findViewById(R.id.itemClick)


        list= listOf()
        myViewModel= ViewModelProvider(this).get(MyViewModel::class.java)
        updatedrecycle()
// ----------------------object of Counter up time----------------------------
        val time: CountUpTimer = object : CountUpTimer(Long.MAX_VALUE) {

            override fun onTick(second: Int) {
                tvTaskTime.setText((second+tempTime).toString())
                current_second=second
            }

        }

//------------------when click on task--------------------------
        itemClick.setOnClickListener {
            if (isStart)
            {
                time.cancel()
                isStart=false
                tempTime+=current_second
            }
            else
            {
                time.start()
                isStart=true
            }
        }

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