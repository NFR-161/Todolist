package com.exampleone.todolist.presentation.panels

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.R
import com.exampleone.todolist.data.Database
import com.exampleone.todolist.data.TaskRepositoryImpl
import com.exampleone.todolist.databinding.AddBinding
import com.exampleone.todolist.domain.*
import com.exampleone.todolist.domain.useCases.*
import com.exampleone.todolist.presentation.TaskFactory
import com.exampleone.todolist.presentation.TaskViewModel


class Add : Fragment() {

    private var binding: AddBinding? = null
    lateinit var getTaskListUseCase: GetTaskListUseCase
    lateinit var deleteTaskUseCase: DeleteTaskUseCase
    lateinit var insertTaskUseCase: InsertTaskUseCase
    lateinit var deleteAllTasksUseCase: DeleteAllTasksUseCase
    lateinit var updateTaskUseCase: UpdateTaskUseCase
    private var taskRepositoryImpl: TaskRepositoryImpl? = null
    private var taskViewModel: TaskViewModel? = null
    private var taskFactory: TaskFactory? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add, container, false)

        // анимация
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)


        val taskDao = Database.getInstance((context as FragmentActivity).application).taskDAO
        taskRepositoryImpl = TaskRepositoryImpl(taskDao)
        getTaskListUseCase = GetTaskListUseCase(taskRepositoryImpl!!)
        deleteTaskUseCase = DeleteTaskUseCase(taskRepositoryImpl!!)
        insertTaskUseCase = InsertTaskUseCase(taskRepositoryImpl!!)
        deleteAllTasksUseCase = DeleteAllTasksUseCase(taskRepositoryImpl!!)
        updateTaskUseCase = UpdateTaskUseCase(taskRepositoryImpl!!)
        taskFactory = TaskFactory(
            getTaskListUseCase,
            deleteTaskUseCase,
            insertTaskUseCase,
            deleteAllTasksUseCase,
            updateTaskUseCase
        )
        taskViewModel = ViewModelProvider(this, taskFactory!!)[TaskViewModel::class.java]

        binding?.saveIT?.setOnClickListener(View.OnClickListener {
            sendTaskToMainAct()
        })

        binding?.topAppBar?.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {

                R.id.close -> {
                    startActivity()

                    true
                }

                R.id.save -> {
                    sendTaskToMainAct()
                    true
                }
                else -> false
            }
        }
        return binding?.root
    }

    // запускаем main activity с анимацией
    private fun startActivity() {
        val options = ActivityOptions.makeSceneTransitionAnimation(context as FragmentActivity)
        startActivity(Intent(context, MainActivity::class.java), options.toBundle())
    }

    private fun sendTaskToMainAct() {
        binding?.apply {
            if (enterTask.text.isNullOrBlank()) {
                enterTask.requestFocus()
            } else {
                taskViewModel?.startInsert(enterTask.text?.toString()!!)
                startActivity()
            }
        }
    }
}




