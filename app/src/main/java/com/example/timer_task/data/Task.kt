package com.example.noteapp_room.data2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class Task (
        @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id:Int = 0,
        @ColumnInfo(name = "taskName") var taskName:String="",
        @ColumnInfo(name = "taskDescription") var taskDescription:String="",
        @ColumnInfo(name = "Timer") var timer:String=""
        )
