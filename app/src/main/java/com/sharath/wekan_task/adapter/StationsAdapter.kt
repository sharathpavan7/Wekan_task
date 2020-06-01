package com.sharath.wekan_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sharath.wekan_task.R
import com.sharath.wekan_task.model.Station
import java.util.*
import kotlin.collections.ArrayList

class StationsAdapter(val stations: ArrayList<Station>):
    RecyclerView.Adapter<StationsAdapter.StationsViewHolder>(), Filterable {


    var stationFilterList = ArrayList<Station>()
    init {
        stationFilterList = stations
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    stationFilterList = stations
                } else {
                    val resultList = ArrayList<Station>()
                    for (station in stations) {
                        if (station.station_name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(station)
                        }
                    }
                    stationFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = stationFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                stationFilterList = results?.values as ArrayList<Station>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsAdapter.StationsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_row, parent, false)
        return StationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationsAdapter.StationsViewHolder, position: Int) {
        if (stationFilterList.size == 0) return
        val station: Station = stationFilterList.get(position)

        val nameTxt: TextView = holder.nameTxt
        nameTxt.text = station.station_name

        val cityTxt: TextView = holder.cityTxt
        cityTxt.text = station.city
    }

    override fun getItemCount(): Int {
        return stationFilterList.size
    }

    class StationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.name_txt)
        val cityTxt: TextView = itemView.findViewById(R.id.city_txt)
    }

}