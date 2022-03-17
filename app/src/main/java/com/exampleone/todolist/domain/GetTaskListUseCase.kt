package com.exampleone.todolist.domain

class GetTaskListUseCase (private val taskRepository: TaskRepository) {
    val tasks = taskRepository.tasks

}