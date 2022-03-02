package com.exampleone.todolist.repositories

import com.exampleone.todolist.models.TaskDao
import com.exampleone.todolist.models.TaskModel

class TaskRepository(private val taskDao: TaskDao) {

    val tasks = taskDao.getAllTasks()

    suspend fun insertTask(taskModel: TaskModel){
        taskDao.insertTask(taskModel)
    }

    suspend fun updateTask(taskModel: TaskModel){
        taskDao.updateTask(taskModel)
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel)
    }

    suspend fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }
}
