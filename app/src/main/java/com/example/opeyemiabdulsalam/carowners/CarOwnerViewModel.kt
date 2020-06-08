package com.example.opeyemiabdulsalam.carowners

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.data.Filter
import com.example.opeyemiabdulsalam.utils.filterCarOwners

class CarOwnerViewModel : ViewModel() {
    private val _carOwnersList = MutableLiveData<List<CarOwner>>()

    val carOwnersList: LiveData<List<CarOwner>>
        get() = _carOwnersList

    fun getCarOwners(filter: Filter){
        _carOwnersList.value = filterCarOwners(filter)
    }
}