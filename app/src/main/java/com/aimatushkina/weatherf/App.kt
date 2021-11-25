package com.aimatushkina.weatherf

import android.app.Application
import android.content.Intent

const val APP_SETTINGS="App settings"
const val IS_STARTED_UP="Is started up"

class App:Application() {
//    здесь будут переменные, доступные во всем проекте
//    TODO переменные БД
    override fun onCreate() {
        super.onCreate()

//    TODO инициализация БД

//    SharedPreferences - пространство, кт создается при установке и не меняется в процессе работы
    val preferences = getSharedPreferences(APP_SETTINGS, MODE_PRIVATE)

    val flag=preferences.contains(IS_STARTED_UP)

    if(!flag){
//        editor собирает изменения
        val editor=preferences.edit()
        editor.putBoolean(IS_STARTED_UP,true)
        editor.apply()
        val intent=Intent(this,InitialActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    }
}