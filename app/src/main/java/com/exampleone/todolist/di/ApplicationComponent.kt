package com.exampleone.todolist.di

import android.app.Application
import com.exampleone.todolist.presentation.panels.Add
import com.exampleone.todolist.presentation.panels.MainActivity
import com.exampleone.todolist.presentation.panels.PanelEditTask
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: Add)

    fun inject(BottomSheetDialogFragment: PanelEditTask)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}