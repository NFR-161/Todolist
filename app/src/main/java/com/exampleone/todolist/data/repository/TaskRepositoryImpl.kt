package com.exampleone.todolist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exampleone.todolist.data.TaskDao
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.data.mapper.TaskMapper
import com.exampleone.todolist.domain.TaskModelInfo
import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor (
    private val taskDao: TaskDao,
    private val mapper: TaskMapper): TaskRepository {

    override suspend fun insertTask(taskModelInfo: TaskModelInfo) {
        taskDao.insertTask(taskModelInfo)

    override suspend fun insertTask(taskModel: TaskModelDb) {
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

    override fun getAllTasks(): LiveData<List<TaskModelDb>> {
          return taskDao.getAllTasks()
    }
}