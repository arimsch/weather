package com.aimatushkina.weatherf.view

import com.aimatushkina.weatherf.buisness.model.DailyWeatherModel
import com.aimatushkina.weatherf.buisness.model.HourlyWeatherModel
import com.aimatushkina.weatherf.buisness.model.WeatherData
import com.aimatushkina.weatherf.presenters.MainPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import java.lang.Error

interface MainView : MvpView{
// @AddToEndSingle не будет добавлять данные в очередь, а запишет туда новые
    @AddToEndSingle
    fun displayLocation(data: String)

    @AddToEndSingle
//    WeatherData будет относиться к классу моделей, т.е хранить информ из интернета
    fun displayCurrentData (data: WeatherData)

    @AddToEndSingle
    fun displayHourlyData(data: List<HourlyWeatherModel>)

    @AddToEndSingle
    fun displayDailyData(data: List<DailyWeatherModel>)

    @AddToEndSingle
    fun displayError (error: Throwable)

    @AddToEndSingle
    fun setLoading(flag : Boolean)



}