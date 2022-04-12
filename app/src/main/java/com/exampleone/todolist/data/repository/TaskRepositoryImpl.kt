package com.exampleone.todolist.data.repository

import androidx.lifecycle.LiveData
import com.exampleone.todolist.data.TaskDao
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun insertTask(taskModel: TaskModel) {
        taskDao.insertTask(taskModel)
    }

    override suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(taskModel)
    }

    override suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel)
    }

    override suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    override fun getAllTasks(): LiveData<List<TaskModel>> {
        return taskDao.getAllTasks()
    }
}