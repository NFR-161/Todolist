package com.exampleone.todolist.domain

import androidx.lifecycle.LiveData
import com.exampleone.todolist.data.TaskModel

interface TaskRepository{

    suspend fun insertTask(taskItem: TaskItem)

    suspend fun updateTask(taskItem: TaskItem)

    suspend fun deleteTask(taskItem: TaskItem)

    suspend fun deleteAllTasks()

    fun getAllTasks(): LiveData<List<TaskItem>>


}
