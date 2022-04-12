package com.exampleone.todolist.domain

import androidx.lifecycle.LiveData
import com.exampleone.todolist.data.TaskModel

interface TaskRepository{

    suspend fun insertTask(taskModel: TaskModel)

    suspend fun updateTask(taskModel: TaskModel)

    suspend fun deleteTask(taskModel: TaskModel)

    suspend fun deleteAllTasks()

    fun getAllTasks(): LiveData<List<TaskModel>>


}
