package com.aimatushkina.weatherf.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

//D - типизация, будет определен только в момент создания класса
abstract class BaseAdapter<D>: RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
//    by lazy поле инициализ только в момент обращения. Лист, кт хранит в себе элементы типа D
//    тут будут данные с сервера
    private val _mData by lazy { mutableListOf<D>() }
    protected val mData: List<D> = _mData

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount()=30//_mData.size

    fun apdateData(data: List<D>){
        if(_mData.isEmpty()&& (data.isNotEmpty())) {
            _mData.addAll(data)
        }else{
            _mData.clear()
            _mData.addAll(data)
        }
//        сообщаем, что данные изменились
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(view: View):RecyclerView.ViewHolder(view){
        abstract fun bindView(position: Int)

    }
}