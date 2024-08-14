package com.santimattius.basic.skeleton

import android.app.Application
import com.santimattius.basic.skeleton.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.com_santimattius_basic_skeleton_di_AppModule
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            allowOverride(false)
            modules(AppModule().module)
            defaultModule()
        }
    }
}