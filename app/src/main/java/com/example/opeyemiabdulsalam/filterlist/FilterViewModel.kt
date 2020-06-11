package com.example.opeyemiabdulsalam.filterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opeyemiabdulsalam.data.DataRepository
import com.example.opeyemiabdulsalam.data.Filter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class FilterViewModel : ViewModel(){

    private val repository = DataRepository.getInstance()

    private var job: Job? = null
    private val _filterList  =  MutableLiveData<List<Filter>>()

    val filterList: LiveData<List<Filter>>
        get() = _filterList

    init {
        getFilters()
    }

    fun getFilters(){
        job?.cancel()
        job = viewModelScope.launch {
            try{
                _filterList.value = repository.getFilterList()
            }catch (e: IOException){
                _filterList.value = listOf()
            }
        }
    }
}