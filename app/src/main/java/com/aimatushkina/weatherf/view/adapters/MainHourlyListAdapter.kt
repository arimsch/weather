package com.aimatushkina.weatherf.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aimatushkina.weatherf.R

class MainHourlyListAdapter: RecyclerView.Adapter<MainHourlyListAdapter.HourlyViewHolder>() {



//    view ссылка на Item, LayoutInflater создает view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
    val view= LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly,parent,false)
    return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class HourlyViewHolder(view: View): RecyclerView.ViewHolder(view){

    }
}