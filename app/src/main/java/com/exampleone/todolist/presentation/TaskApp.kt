package com.exampleone.todolist.presentation

import android.app.Application
import com.exampleone.todolist.di.DaggerApplicationComponent

class TaskApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}