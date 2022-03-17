package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskRepository

class GetTaskListUseCase (private val taskRepository: TaskRepository) {
    val tasks = taskRepository.tasks

}