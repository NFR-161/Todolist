package com.exampleone.todolist.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao{

    @Insert
    suspend fun insertTask(taskModel: TaskModel)

    @Update
    suspend fun updateTask(taskModel: TaskModel)

    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

    @Query("DELETE FROM task_data_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM task_data_table")
    fun getAllTasks(): LiveData<List<TaskModel>>
}