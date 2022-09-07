package com.exampleone.todolist.presentation.panels

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.AddBinding
import com.exampleone.todolist.di.TaskApp
import com.exampleone.todolist.presentation.viewmodels.TaskFactory
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import javax.inject.Inject


class Add : Fragment() {

    private var _binding: AddBinding? = null

    private val binding: AddBinding
        get() = _binding ?: throw RuntimeException("FragmentAdd is null")

    lateinit var taskViewModel: TaskViewModel

    @Inject
    lateinit var taskFactory: TaskFactory

    private val component by lazy {
        (requireActivity().application as TaskApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.add, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this, taskFactory)[TaskViewModel::class.java]

        binding.saveIT.setOnClickListener(View.OnClickListener {
            sendTaskToMainAct()
        })
        closeOrSafe()

    }

    private fun backToActivity() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.remove(this)
            ?.commit()

    }

    private fun sendTaskToMainAct() {
        binding.apply {
            if (enterTask.text.isNullOrBlank()) {
                enterTask.requestFocus()
            } else {
                taskViewModel.startInsert(enterTask.text?.toString()!!)
                backToActivity()
            }
        }
    }

    companion object {
        fun newInstance() = Add()
    }

    private fun closeOrSafe() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {

                R.id.close -> {
                    backToActivity()

                    true
                }

                R.id.save -> {
                    sendTaskToMainAct()
                    true
                }
                else -> false
            }
        }
    }

}




