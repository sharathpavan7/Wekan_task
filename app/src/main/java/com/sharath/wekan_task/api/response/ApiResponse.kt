package com.sharath.wekan_task.api.response

import com.sharath.wekan_task.model.Station

data class ApiResponse(
    val fuel_stations: List<Station>
)