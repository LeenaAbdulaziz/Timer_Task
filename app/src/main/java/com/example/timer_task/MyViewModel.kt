package com.example.timer_task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp_room.data2.Task
import com.example.noteapp_room.data2.TaskDatabase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyViewModel (activity: Application): AndroidViewModel(activity){
    private val notes:LiveData<List<Task>>

    val ob= TaskDatabase.getinstant(activity)

    init {

        notes=ob.TaskDao().getAlTasks()





    }

    fun getTasks(): LiveData<List<Task>>{
        return notes
    }
    fun addTasks(taskName: String,taskDescription:String) {

        GlobalScope.launch(Main)
        {
  ob.TaskDao().addTask(Task(0,taskName,taskDescription))

            }
        }
    fun updatesTasks(id:Int,taskName: String,taskDescription:String) {
        GlobalScope.launch(Main)
        {
            ob.TaskDao().updateTask(Task(id, taskName,taskDescription))

        }
    }

    fun deleteTasks(id:Int) {
        GlobalScope.launch(Main)
        {
            ob.TaskDao().deleteTask(Task(id))

        }
    }
    }

