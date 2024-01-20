package com.wahyuhw.mealrecipes.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE
import org.koin.core.module.Module

class MealRecipesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(NONE)
            androidContext(this@MealRecipesApplication)
            modules(getModules())
        }
    }
}

fun getModules(): List<Module> =
    listOf(
    
    )