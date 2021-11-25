package com.aimatushkina.weatherf.presenters

import com.aimatushkina.weatherf.view.MainView

class MainPresenter : BasePresenter<MainView>() {

    //TODO тут будут переменные репрозитория
    override fun enable() {

    }

    fun refresh (lat: String, lon:String){
        viewState.setLoading(true)
        //TODO тут будет обращение к репрозиторию
    }
}