package com.aimatushkina.weatherf

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aimatushkina.weatherf.buisness.model.DailyWeatherModel
import com.aimatushkina.weatherf.buisness.model.HourlyWeatherModel
import com.aimatushkina.weatherf.buisness.model.WeatherData
import com.aimatushkina.weatherf.databinding.ActivityMainBinding
import com.aimatushkina.weatherf.presenters.MainPresenter
import com.aimatushkina.weatherf.view.MainView
import com.aimatushkina.weatherf.view.adapters.MainDailyListAdapter
import com.aimatushkina.weatherf.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


const val TAG="geo-test"
class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private lateinit var binding: ActivityMainBinding
    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var m_location: Location



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //        layoutManager отвечает за расположение элементов (прокрутку)
//        setMasFixedSize не будет динамичиски изменять размер item
        binding.mainHourlyList.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        binding.mainDailyList.apply {
            adapter = MainDailyListAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }

//        начало прослушки данных
        mainPresenter.enable()

        geoService.requestLocationUpdates(locationRequest,geoCallback,null)
    }

    // -----       moxy code     -----
    override fun displayLocation(data: String) {
        binding.mainCityNameTv.text=data
    }

    override fun displayCurrentData(data: WeatherData) {

    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
//        binding.mainHourlyList.adapter возвращает данные типа адаптер, MainHourlyListAdapter - наследник от этотго адаптера
        (binding.mainHourlyList.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (binding.mainDailyList.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(error: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }
    // -----       /moxy code     -----

    // -----       location code     -----
        private fun initLocationRequest(): LocationRequest{
            val request=LocationRequest.create()
            return request.apply {
                interval=10000 //10 sec
                fastestInterval=5000
                priority=LocationRequest.PRIORITY_HIGH_ACCURACY //очень энергозатратно 1. Класс, кт включает и отключает прослушку динамически
           }
        }
//    object - анонимный объект
//    geoCallback функция обратного вызова
    private val geoCallback=object:LocationCallback(){
    override fun onLocationResult(geo: LocationResult) {
        Log.d(TAG,"r: ${geo.locations.size}")
        for (location in geo.locations)
            m_location=location
        mainPresenter.refresh(m_location.latitude.toString(),m_location.longitude.toString())
        Log.d(TAG, "lat: ${m_location.latitude}")
    }
    }


    // -----       /location code     -----

}