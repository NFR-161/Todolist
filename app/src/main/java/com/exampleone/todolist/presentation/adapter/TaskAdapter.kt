package com.exampleone.todolist.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.TaskItemBinding
import com.exampleone.todolist.domain.TaskItem
import com.google.android.material.checkbox.MaterialCheckBox

class TaskAdapter(
    private val strikeThrough: (MaterialCheckBox, TaskItem) -> Unit,
    private val startPencil: () -> Unit,
    private val editTask: (TaskItem) -> Unit

) : ListAdapter<TaskItem, TaskHolder>(TaskInfoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TaskItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.task_item,
            parent,
            false
        )
        return TaskHolder(binding)

    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(currentList[position], strikeThrough, startPencil, editTask)

    }
}


