package com.example.timer_task

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp_room.data2.Task
import kotlinx.android.synthetic.main.card_task.view.*


class recyclerView (private val activity: Activity): RecyclerView.Adapter<recyclerView.ItemHolder>() {
    private var taskContent = emptyList<Task>()
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_task, parent, false))
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val tasks=taskContent[position]
        holder.itemView.apply {
            tvTaskName.text = tasks.taskName
          // desc.text= tasks.taskDescription
            tvTaskTime.text=tasks.timer

            //onClick listener if the user click on the task layout
            taskLayout.setOnClickListener {

               /// ex stop the time

           }


        }
    }

    override fun getItemCount()= taskContent.size

}