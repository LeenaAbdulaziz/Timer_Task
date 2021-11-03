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
    var TimeOff = true
var task1:Boolean=false
    var fixedtime=0

   //var myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_task, parent, false))
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tasks=taskContent[position]
        holder.itemView.apply {
            tvTaskName.text = tasks.taskName

          // desc.text= tasks.taskDescription
            tvTaskTime.text=tasks.timer
            var isStart=false
            var tempTime=0
            var current_second=0

            var taskid=tasks.id
            var prevousid=taskid
            val time: CountUpTimer = object : CountUpTimer(Long.MAX_VALUE) {

                override fun onTick(second: Int) {
                    tvTaskTime.setText((second+tempTime).toString())
                    current_second=second
                    fixedtime=second+tempTime
                }



            }
            fun counter(){
                var tasks=taskContent[position]
                if (TimeOff == true) { // ckeck other task
                    if (tasks.timer_state == false) {

                       // activity.myViewModel.getTasks()
                        tempTime=tasks.timer.toInt()
                        time.start()
                        tasks.timer_state = true
                        TimeOff = false

                    } else {
                        tempTime=fixedtime
                        tasks.timer = tempTime.toString()


                        time.cancel()
                        tasks.timer_state = false
                        activity.update(tasks)
                    }
                } else {
                    Toast.makeText(activity,
                        "you should close task first",
                     Toast.LENGTH_SHORT).show()
                    tasks.timer = tempTime.toString()
                    // myViewModel.addedit(tasks)
                    time.cancel()
                    tasks.timer_state = false
                    TimeOff = true
                    activity.update(tasks)

                }

            }

            //onClick listener if the user click on the task layout
            itemClick.setOnClickListener {

                counter()

           }



        }
    }

    override fun getItemCount()= taskContent.size


    }



