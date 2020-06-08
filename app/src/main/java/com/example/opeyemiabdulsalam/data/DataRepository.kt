package com.example.opeyemiabdulsalam.data

import com.example.opeyemiabdulsalam.network.FilterApi
import com.example.opeyemiabdulsalam.network.FilterApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DataRepository (
    filterApiService: FilterApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
){
    companion object {
        private var instance: DataRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DataRepository(FilterApi.retrofitService).also { instance = it }
            }
    }

    private val request = filterApiService.getFilters()

    suspend fun getFilterList(): List<Filter> = request.await()
}