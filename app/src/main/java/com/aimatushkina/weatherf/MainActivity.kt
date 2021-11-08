package com.aimatushkina.weatherf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aimatushkina.weatherf.databinding.ActivityMainBinding
import com.aimatushkina.weatherf.view.adapters.MainHourlyListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        layoutManager отвечает за расположение элементов (прокрутку)
//        setMasFixedSize не будет динамичиски изменять размер item
        binding.mainHourlyList.apply {
            adapter= MainHourlyListAdapter()
            layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }
}