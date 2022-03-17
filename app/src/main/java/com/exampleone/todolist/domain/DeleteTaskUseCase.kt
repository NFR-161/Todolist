package com.exampleone.todolist.domain

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun deleteTask(taskModel: TaskModel) {
        taskRepository.deleteTask(taskModel)
    }
}