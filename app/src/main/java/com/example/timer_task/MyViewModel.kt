package com.example.timer_task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp_room.data2.Task
import com.example.noteapp_room.data2.TaskDatabase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyViewModel (activity: Application): AndroidViewModel(activity)
{
    private val tasks:LiveData<List<Task>>

    val ob= TaskDatabase.getinstant(activity)

    init {
        tasks=ob.TaskDao().getAlTasks()
    }

    fun getTasks(): LiveData<List<Task>>
    {
        return tasks
    }
    fun addTasks(task:Task)
    {

        GlobalScope.launch(Main)
        {
            ob.TaskDao().addTask(Task(0,task.taskName,task.taskDescription,0,false))
        }
    }
    fun updatesTasks(task: Task)
    {
        GlobalScope.launch(Main)
        {
            ob.TaskDao().updateTask(
                Task(task.id, task.taskName, task.taskDescription, task.timer, task.timer_state)
            )
        }
    }

//    fun deleteTasks(id:Int) {
//        GlobalScope.launch(Main)
//        {
//            ob.TaskDao().deleteTask(Task(id)
//
//        }
//    }
}

