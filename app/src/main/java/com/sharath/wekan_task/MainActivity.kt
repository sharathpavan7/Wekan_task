package com.sharath.wekan_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sharath.wekan_task.adapter.StationsAdapter
import com.sharath.wekan_task.api.ApiAdapter
import com.sharath.wekan_task.api.ApiService
import com.sharath.wekan_task.api.response.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStationList()
    }

    fun getStationList() {
        val request = ApiAdapter.retrofit().create(ApiService::class.java)
        val call = request.getList()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = StationsAdapter(response.body()!!.fuel_stations)
                    }
                    progressBar.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }
}
