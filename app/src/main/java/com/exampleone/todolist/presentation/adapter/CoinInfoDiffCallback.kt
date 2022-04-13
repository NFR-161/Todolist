package com.exampleone.todolist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.exampleone.todolist.data.TaskModel

object TaskInfoDiffCallback : DiffUtil.ItemCallback<TaskModel>() {

    override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem == newItem
    }
}
