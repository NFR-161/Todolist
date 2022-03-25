package com.exampleone.todolist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.exampleone.todolist.data.TaskModel
import com.exampleone.todolist.domain.TaskItem

object TaskInfoDiffCallback : DiffUtil.ItemCallback<TaskItem>() {

    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
        return oldItem == newItem
    }
}
