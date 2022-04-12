package com.exampleone.todolist.presentation.panels

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.R
import com.exampleone.todolist.data.Database
import com.exampleone.todolist.data.TaskRepositoryImpl
import com.exampleone.todolist.databinding.PanelEditTaskBinding
import com.exampleone.todolist.domain.*
import com.exampleone.todolist.domain.useCases.*
import com.exampleone.todolist.presentation.TaskFactory
import com.exampleone.todolist.presentation.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PanelEditTask : BottomSheetDialogFragment(), View.OnKeyListener {

    private var binding: PanelEditTaskBinding? = null
    private var taskRepositoryImpl: TaskRepositoryImpl? = null
    private var taskViewModel: TaskViewModel? = null
    private var taskFactory: TaskFactory? = null
    private var idTask: Int? = null
    lateinit var getTaskListUseCase: GetTaskListUseCase
    lateinit var deleteTaskUseCase: DeleteTaskUseCase
    lateinit var insertTaskUseCase: InsertTaskUseCase
    lateinit var deleteAllTasksUseCase: DeleteAllTasksUseCase
    lateinit var updateTaskUseCase: UpdateTaskUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.panel_edit_task, container, false)

        idTask = arguments?.getString("idTask")?.toInt()
        binding?.editTask?.setText(arguments?.getString("nameTask").toString())

        val categoriesDao = Database.getInstance((context as FragmentActivity).application).taskDAO
        taskRepositoryImpl = TaskRepositoryImpl(categoriesDao)
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

        binding?.editTask?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.editTask -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.apply {
                        if (!editTask.text.toString().isNullOrBlank())
                            taskViewModel?.startUpdateTask(
                                idTask.toString().toInt(),
                                editTask.text?.toString()!!
                            )
                        editTask.setText("")
                        dismiss()
                        val options =
                            ActivityOptions.makeSceneTransitionAnimation(context as FragmentActivity)
                        startActivity(Intent(context, MainActivity::class.java), options.toBundle())
                        return true
                    }

                }
            }
        }
        return false
    }

}