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
import com.exampleone.todolist.data.TaskModel
import com.google.android.material.checkbox.MaterialCheckBox
import java.util.*

class TaskAdapter(private val deleteTask:(TaskModel)->Unit, private val strikeThrough:(nameT: MaterialCheckBox, taskModel: TaskModel)->Unit, private val startPencil:()->Unit): RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private val tasksList = ArrayList<TaskModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TaskItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.task_item, parent, false)
        return TaskHolder(binding)

    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(tasksList[position], deleteTask,strikeThrough,startPencil)

    }

    fun setList(tasks: List<TaskModel>) {
        tasksList.clear()
        tasksList.addAll(tasks)

    }

    class TaskHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            task: TaskModel,
            deleteTask: (TaskModel) -> Unit,
            strikeThrough: (nameT: MaterialCheckBox, taskModel: TaskModel) -> Unit,
            startPencil: () -> Unit
        ) {

            if(task.isDone){
                val sp = SpannableString(task.name)
                sp.setSpan(StrikethroughSpan(), 0, task.name.length, 0)
                binding.nameTask.text = sp
                binding.nameTask.isChecked = true

            } else {
                binding.nameTask.text = task.name
                binding.nameTask.isChecked = false
            }
                binding.nameTask.setOnClickListener(View.OnClickListener {
                    strikeThrough(binding.nameTask,task)
                    if (binding.nameTask.isChecked){
                        startPencil()
                    }
                })
        }
    }
}
