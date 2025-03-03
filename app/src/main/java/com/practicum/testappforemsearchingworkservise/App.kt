package com.practicum.testappforemsearchingworkservise

import android.app.Application
import com.practicum.testappforemsearchingworkservise.di.dataModule
import com.practicum.testappforemsearchingworkservise.di.interactorModule
import com.practicum.testappforemsearchingworkservise.di.repositoryModule
import com.practicum.testappforemsearchingworkservise.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf( dataModule, repositoryModule, interactorModule, viewModelModule ))
        }
    }
}
