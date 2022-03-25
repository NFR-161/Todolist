package com.exampleone.todolist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exampleone.todolist.data.TaskDao
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.data.mapper.TaskMapper
import com.exampleone.todolist.domain.TaskItem
import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    private val mapper = TaskMapper()

    override suspend fun insertTask(taskItem: TaskItem) {
        taskDao.insertTask(mapper.mapTaskItemToDbModel(taskItem))
    }

    override suspend fun updateTask(taskItem: TaskItem) {
        taskDao.updateTask(mapper.mapTaskItemToDbModel(taskItem))
    }

    override suspend fun deleteTask(taskItem: TaskItem) {
        taskDao.deleteTask(mapper.mapTaskItemToDbModel(taskItem))
    }

    override suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }


    override fun getAllTasks(): LiveData<List<TaskItem>> {
        return Transformations.map(taskDao.getAllTasks()) {
            it.map {
                mapper.mapDbModelToTaskItem(it)
            }
        }
    }
}