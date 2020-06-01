package com.sharath.wekan_task.api

import com.sharath.wekan_task.api.response.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/alt-fuel-stations/v1.json?api_key=DEMO_KEY&fuel_type=E85,ELEC&state=CA&limit=100")
    fun getList() : Call<ApiResponse>
}