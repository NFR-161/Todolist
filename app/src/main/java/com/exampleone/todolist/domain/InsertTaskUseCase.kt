package com.exampleone.todolist.domain

class InsertTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun insertTask(task: TaskModel) {
        taskRepository.insertTask(task)
    }
}