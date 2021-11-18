package com.aimatushkina.weatherf.view.adapters

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.aimatushkina.weatherf.R
import com.aimatushkina.weatherf.buisness.model.HourlyWeatherModel
import com.aimatushkina.weatherf.databinding.ActivityMainBinding
import com.google.android.material.textview.MaterialTextView

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {


    //    view ссылка на Item, inflater создает view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly, parent, false)
        return HourlyViewHolder(view)
    }

    inner class HourlyViewHolder(view: View) : BaseViewHolder(view) {

        @BindView(R.id.item_hourly_time_tv)
        lateinit var time: MaterialTextView

        @BindView(R.id.item_hourly_temp_tv)
        lateinit var temperature: MaterialTextView

        @BindView(R.id.item_hourly_pop_tv)
        lateinit var popRait: MaterialTextView

        @BindView(R.id.item_hourly_weather_condition_icon)
        lateinit var icon: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        //        метод перезаписи данных при прокручивании
        override fun bindView(position: Int) {
            time.text = "14:00"
            temperature.text = "14"
            popRait.text = "100%"
            icon.setImageResource(R.drawable.ic_sun)
        }

    }
}