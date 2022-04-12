package com.exampleone.todolist.domain.useCases

import com.exampleone.todolist.domain.TaskRepository
import javax.inject.Inject

class GetTaskListUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    val tasks = taskRepository.getAllTasks()

}