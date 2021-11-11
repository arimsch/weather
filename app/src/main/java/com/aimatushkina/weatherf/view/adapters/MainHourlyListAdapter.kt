package com.aimatushkina.weatherf.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.aimatushkina.weatherf.R
import com.aimatushkina.weatherf.buisness.model.HourlyWeatherModel
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

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {

        }

    }
}