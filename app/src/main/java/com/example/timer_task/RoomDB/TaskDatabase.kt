package com.example.noteapp_room.data2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class],version = 2,exportSchema = false)

abstract class TaskDatabase: RoomDatabase() {

        companion object{
            var instant: TaskDatabase?=null
            fun getinstant(context: Context): TaskDatabase {
                if(instant !=null)
                {
                    return instant as TaskDatabase
                }
                instant = Room.databaseBuilder(context, TaskDatabase::class.java,"name").run{
                    allowMainThreadQueries() }.build()
                return instant as TaskDatabase
            }
        }
        abstract fun TaskDao(): TaskDao;
    }
