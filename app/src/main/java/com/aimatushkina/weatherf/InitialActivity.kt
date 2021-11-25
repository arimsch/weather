package com.aimatushkina.weatherf

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

const val GEO_LOCATION_REQUEST_COD_SUCCESS=1
class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        checkPermission()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
// TODO тут будет запуск MainActivity
    }

    private fun checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED    ){
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