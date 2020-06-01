package com.sharath.wekan_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
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

    lateinit var adapter: StationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        getStationList()
    }

    fun getStationList() {
        val request = ApiAdapter.retrofit().create(ApiService::class.java)
        val call = request.getList()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    adapter = StationsAdapter(response.body()!!.fuel_stations)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = this@MainActivity.adapter
                    }
                    progressBar.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

            }
        })
    }
}
