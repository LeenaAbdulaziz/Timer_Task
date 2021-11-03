package com.example.noteapp_room.data2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM Tasks ORDER BY id")
    fun getAlTasks(): LiveData<List<Task>>

    @Insert
    fun addTask(n: Task)
    @Update
    fun updateTask(note: Task)
    @Delete
    fun deleteTask(note: Task)

}