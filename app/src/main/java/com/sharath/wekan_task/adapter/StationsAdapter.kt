package com.sharath.wekan_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sharath.wekan_task.R
import com.sharath.wekan_task.model.Station

class StationsAdapter(val stations: List<Station>): RecyclerView.Adapter<StationsAdapter.StationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsAdapter.StationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_row, parent, false)
        return StationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationsAdapter.StationsViewHolder, position: Int) {
        val station: Station = stations.get(position)

        val nameTxt: TextView = holder.nameTxt
        nameTxt.text = station.station_name

        val cityTxt: TextView = holder.cityTxt
        cityTxt.text = station.city
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    class StationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.name_txt)
        val cityTxt: TextView = itemView.findViewById(R.id.city_txt)
    }

}