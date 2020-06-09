package com.example.opeyemiabdulsalam.carowners

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.opeyemiabdulsalam.R
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.data.Filter
import com.example.opeyemiabdulsalam.utils.filterCarOwners
import com.example.opeyemiabdulsalam.utils.readDataFromRaw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CarOwnerViewModel(application: Application, private val filter: Filter) :
    AndroidViewModel(application) {

    private var job: Job? = null

    private val _carOwnersList = MutableLiveData<List<CarOwner>>()

    private var carOwners = ArrayList<CarOwner>()

    val carOwnersList: LiveData<List<CarOwner>>
        get() = _carOwnersList

    init {
        job = viewModelScope.launch {
            getCarOwners()
        }
    }

    suspend fun getCarOwners() {
        withContext(Dispatchers.IO) {
            try {
                carOwners =
                    readDataFromRaw(getApplication<Application>().resources.openRawResource(R.raw.car_data))
                _carOwnersList.postValue(filterCarOwners(filter, carOwners))
            } catch (e: IOException) {
                _carOwnersList.value = listOf()
            }
        }
    }
}


@Suppress("UNCHECKED_CAST")
class CarOwnerViewModelFactory(private val application: Application, private val filter: Filter) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        (CarOwnerViewModel(application, filter) as T)
}