package com.exampleone.todolist.presentation


import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.TaskItemBinding
import com.exampleone.todolist.domain.TaskModel
import com.google.android.material.checkbox.MaterialCheckBox
import java.util.*

class TaskAdapter(
    private val strikeThrough: (nameT: MaterialCheckBox, taskModel: TaskModel) -> Unit,
    private val startPencil: () -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    val tasksList = ArrayList<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingTI: TaskItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.task_item,
            parent,
            false
        )
        return TaskHolder(bindingTI)

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(tasksList[position], strikeThrough, startPencil)

    }

    fun setList(tasks: List<TaskModel>) {
        tasksList.clear()
        tasksList.addAll(tasks)

    }

    class TaskHolder(private val bindingTaskIt: TaskItemBinding) :
        RecyclerView.ViewHolder(bindingTaskIt.root) {

        fun bind(
            task: TaskModel,
            strikeThrough: (nameT: MaterialCheckBox, taskModel: TaskModel) -> Unit,
            startPencil: () -> Unit
        ) {

            if (task.isDone) {
                val sp = SpannableString(task.name)
                sp.setSpan(StrikethroughSpan(), 0, task.name.length, 0)
                bindingTaskIt.nameTask.text = sp
                bindingTaskIt.nameTask.isChecked = true

            } else {
                bindingTaskIt.nameTask.text = task.name
                bindingTaskIt.nameTask.isChecked = false
            }
            bindingTaskIt.nameTask.setOnClickListener(View.OnClickListener {
                strikeThrough(bindingTaskIt.nameTask, task)
                if (bindingTaskIt.nameTask.isChecked) {
                    startPencil()

                }
            })

        }
    }
}
