package com.exampleone.todolist.presentation.panels

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.exampleone.todolist.R
import com.exampleone.todolist.databinding.AddBinding
import com.exampleone.todolist.di.TaskApp
import com.exampleone.todolist.presentation.viewmodels.TaskFactory
import com.exampleone.todolist.presentation.viewmodels.TaskViewModel
import javax.inject.Inject


class Add : Fragment() {

    lateinit var binding: AddBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.add, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        taskViewModel = ViewModelProvider(this, taskFactory)[TaskViewModel::class.java]
        binding.saveIT.setOnClickListener(View.OnClickListener {
            sendTaskToMainAct()
        })
        closeOrSafe()
    }

    // запускаем main activity с анимацией
    private fun startActivity() {
        val options = ActivityOptions.makeSceneTransitionAnimation(context as FragmentActivity)
        startActivity(Intent(context, MainActivity::class.java), options.toBundle())
    }

    private fun sendTaskToMainAct() {
        binding.apply {
            if (enterTask.text.isNullOrBlank()) {
                enterTask.requestFocus()
            } else {
                taskViewModel.startInsert(enterTask.text?.toString()!!)
                startActivity()
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
    }

}




