package com.example.opeyemiabdulsalam.network

import androidx.lifecycle.LiveData
import com.example.opeyemiabdulsalam.data.Filter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://ven10.co/"

interface FilterApiService {


    @GET("assessment/filter.json")
    fun getFilters(): Deferred<List<Filter>>
}

private val moshi= Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object FilterApi{
 val retrofitService: FilterApiService by lazy{ retrofit.create(FilterApiService::class.java)}
}