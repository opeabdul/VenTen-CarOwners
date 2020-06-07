package com.example.opeyemiabdulsalam.network

import androidx.lifecycle.LiveData
import com.example.opeyemiabdulsalam.data.Filter
import retrofit2.http.GET

interface ServiceGenerator {
    @GET("assessment/filter.json")
    fun getFilters(): LiveData<Filter>
}