package com.exampleone.todolist.di

import android.app.Application

class TaskApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}