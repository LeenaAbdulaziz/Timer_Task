package com.example.timer_task

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.card_task.view.*

class recyclerView (private val activity: Activity, private val taskContant: ArrayList<ArrayList<String>>): RecyclerView.Adapter<recyclerView.ItemHolder>() {
    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_task, parent, false))
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        holder.itemView.apply {
           // task.text = taskContant[position][0]
           /// desc.text= taskContant[position][1]
           // time.text=taskContant[position][2]

            //card.setOnClickListener {

               /// Alert()

          //  }


        }
    }

    override fun getItemCount()= taskContant.size

}