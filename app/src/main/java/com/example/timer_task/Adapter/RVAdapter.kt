package com.example.timer_task.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import com.example.timer_task.CountUpTimer
import com.example.timer_task.MyViewModel
import com.example.timer_task.Pages.MainActivity
import com.example.timer_task.R
import kotlinx.android.synthetic.main.card_task.view.*



class recyclerView (private var taskContent: List<Task>,private val activity: MainActivity): RecyclerView.Adapter<recyclerView.ItemHolder>()
{

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var fixedtime=0
    var currentId=-1
    var lastId=-1
    val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder
    {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_task, parent, false))
    }



    override fun onBindViewHolder(holder: ItemHolder, position: Int)
    {
        val tasks=taskContent[position]
        holder.itemView.apply {

            fun updateTime(startup:Boolean, courrentSecound:Int?=0)
            {
                if (startup)
                {
                    var hours = (tasks.timer) / 3600;
                    var minutes = ((tasks.timer) % 3600) / 60;
                    var seconds = (tasks.timer) % 60;
                    tvTaskTime.text = String.format("%01d:%02d:%02d", hours, minutes, seconds)
                }
                else
                {
                    var hours = (tasks.timer + courrentSecound!!) / 3600
                    var minutes = ((tasks.timer + courrentSecound!!) % 3600) / 60
                    var seconds = (tasks.timer + courrentSecound!!) % 60
                    var timeString = String.format("%01d:%02d:%02d", hours, minutes, seconds)
                    tvTaskTime.setText(timeString)
                }

            }

            updateTime(true)

            tvTaskName.text = tasks.taskName
            tvDescription.text = tasks.taskDescription

            var tempTime = 0
            var current_second = 0
            val time: CountUpTimer = object : CountUpTimer(Long.MAX_VALUE)
            {
                override fun onTick(courrentSecound: Int)
                {
                   updateTime(false,courrentSecound)
                    current_second = courrentSecound
                    fixedtime = courrentSecound + tempTime

                }
            }

            fun counter()
            {
                if (currentId!=-1)
                {
                    currentId=tasks.id
                }
                if (currentId==lastId)
                {
                    if (tasks.timer_state == false)
                    {
                        tempTime = tasks.timer!!
                        tasks.timer_state = true
                        currentId=tasks.id
                        if (lastId==-1)
                        {
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




            //onClick listener if the user click on the task layout
            itemClick.setOnClickListener {
              counter()
            }
        }
    }


    override fun getItemCount()= taskContent.size


}



