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
import com.aimatushkina.weatherf.databinding.ActivityMainBinding
import com.aimatushkina.weatherf.view.adapters.MainDailyListAdapter
import com.aimatushkina.weatherf.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val GEO_LOCATION_REQUEST_COD_SUCCESS=1
const val TAG="geo-test"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var m_location: Location



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        checkPermission()


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

        geoService.requestLocationUpdates(locationRequest,geoCallback,null)
    }

        //        location code
        private fun initLocationRequest(): LocationRequest{
            val request=LocationRequest.create()
            return request.apply {
                interval=10000 //10 sec
                fastestInterval=5000
                priority=LocationRequest.PRIORITY_HIGH_ACCURACY //очень энергозатратно 1. Класс, кт включает и отключает прослушку динамически
           }
        }
//    object - анонимный объект
    private val geoCallback=object:LocationCallback(){
    override fun onLocationResult(geo: LocationResult) {
        Log.d(TAG,"r: ${geo.locations.size}")
        for (location in geo.locations)
            m_location=location
        // TODO будет вызов презентора
        Log.d(TAG, "lat: ${m_location.latitude}")
    }
    }

    //    Start initial activity code
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.d(TAG,"ght:$requestCode")
// TODO тут будет запуск MainActivity
    }

    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED    ){
            MaterialAlertDialogBuilder(this)
                .setTitle("Нам нужны геоданные")
                .setMessage("Пожалйста, разрешите доступ к данным для продолжения работы")
                .setPositiveButton("ok"){
                    _,_-> ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), GEO_LOCATION_REQUEST_COD_SUCCESS)
                }
                .setPositiveButton("ok"){
                        _,_-> ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), GEO_LOCATION_REQUEST_COD_SUCCESS)
                }
                .setNegativeButton("Create"){dialog,_->dialog.dismiss()}
                .create()
                .show()
        }
    }
}