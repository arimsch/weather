package com.aimatushkina.weatherf.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.aimatushkina.weatherf.R
import com.aimatushkina.weatherf.buisness.model.DailyWeatherModel
import com.aimatushkina.weatherf.databinding.ItemMainDailyBinding
import com.google.android.material.textview.MaterialTextView

class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily, parent, false)
        return DailyViewHolder(view)
    }

    inner class DailyViewHolder(view: View) : BaseViewHolder(view) {

        @BindView(R.id.item_daily_date_tv)
        lateinit var date:MaterialTextView
        @BindView(R.id.item_daily_pop_tv)
        lateinit var popRait:MaterialTextView
        @BindView(R.id.item_daily_max_temp_tv)
        lateinit var maxTemp:MaterialTextView
        @BindView(R.id.item_daily_min_temp_tv)
        lateinit var minTemp:MaterialTextView
        @BindView(R.id.item_daily_weather_condition_icon)
        lateinit var icon:ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {
            date.text= "25 Sat"
            popRait.text="25%"
            maxTemp.text="20"
            minTemp.text="10"
            icon.setImageResource(R.drawable.ic_sun)
        }

    }
}