package com.exampleone.todolist.domain

class DeleteAllTasksUseCase(private val taskRepository: TaskRepository) {

    suspend fun deleteAllTasks() {
        taskRepository.deleteAllTasks()
    }
}