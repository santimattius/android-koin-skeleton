package com.santimattius.basic.skeleton

import android.app.Application
import com.santimattius.basic.skeleton.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application(), KoinStartup {

    override fun onKoinStartup(): KoinConfiguration = KoinConfiguration {
        androidContext(this@MainApplication)
        allowOverride(false)
        modules(AppModule().module)
        defaultModule()
    }
}