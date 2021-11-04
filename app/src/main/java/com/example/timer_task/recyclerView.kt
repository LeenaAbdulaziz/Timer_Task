package com.example.timer_task

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import kotlinx.android.synthetic.main.card_task.view.*



    class recyclerView (private var taskContent: List<Task>,private val activity: MainActivity): RecyclerView.Adapter<recyclerView.ItemHolder>() {

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var task1:Boolean=false
    var fixedtime=0
    var currentId=-1
        var lastId=-1
    val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_task, parent, false))
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tasks=taskContent[position]
        holder.itemView.apply {
            tvTaskTime.text = tasks.timer.toString()

            tvTaskName.text = tasks.taskName

            var tempTime = 0
            var current_second = 0
            val time: CountUpTimer = object : CountUpTimer(Long.MAX_VALUE) {
                override fun onTick(second: Int) {
                    tvTaskTime.setText((second + tempTime).toString())
                    current_second = second
                    fixedtime = second + tempTime
                }
            }
//            fun counter(){
////                if (timeOff  )
////                { // ckeck other task
//                    if (tasks.timer_state == false)
//                    {
//                       // activity.myViewModel.getTasks()
//                        tempTime= tasks.timer!!
//                        tasks.timer_state = true
//                        myViewModel.updatesTasks(tasks)
//                        timeOff = false
//                        time.start()
//
//                    }
//                    else
//                    {
//                        tempTime=fixedtime
//                        tasks.timer = tempTime
//                        tasks.timer_state = false
//                        myViewModel.updatesTasks(tasks)
//                        timeOff=true
//                        time.cancel()
//                    }
//                }
//                else
//                {
//                    Toast.makeText(activity, "you should close task first", Toast.LENGTH_SHORT).show()
//                }


            //onClick listener if the user click on the task layout
            itemClick.setOnClickListener {
                if (currentId!=-1){
                    currentId=tasks.id
                }
                if (currentId==lastId) {
                    if (tasks.timer_state == false)
                    {
                        tempTime = tasks.timer!!
                        tasks.timer_state = true
                        currentId=tasks.id
                        if (lastId==-1){
                            lastId=tasks.id
                        }
                        time.start()
                    }
                    else
                    {
                        tempTime = fixedtime
                        tasks.timer = tempTime
                        tasks.timer_state = false
                        lastId=tasks.id
                        time.cancel()
                        myViewModel.updatesTasks(tasks)
                    }
                }
                else
                {
                    Toast.makeText(activity, "close last task", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount()= taskContent.size


    }



