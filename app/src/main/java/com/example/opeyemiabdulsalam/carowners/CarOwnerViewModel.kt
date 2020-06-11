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
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class CarOwnerViewModel(application: Application, private val filter: Filter) :
    AndroidViewModel(application) {

    private var job: Job? = null
    private var carOwners = ArrayList<CarOwner>()

    private val _carOwnersList = MutableLiveData<List<CarOwner>>()
    val carOwnersList: LiveData<List<CarOwner>>
        get() = _carOwnersList

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent


    init {
        job = viewModelScope.launch {
            getCarOwners()
        }
    }

    suspend fun getCarOwners() {
        withContext(Dispatchers.IO) {
            try {
                carOwners = readDataFromRaw()
                _carOwnersList.postValue(filterCarOwners(filter, carOwners))
            } catch (e: IOException) {
                _carOwnersList.postValue(listOf())
                _showSnackbarEvent.postValue(true)
            }
        }
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }
}


@Suppress("UNCHECKED_CAST")
class CarOwnerViewModelFactory(private val application: Application, private val filter: Filter) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        (CarOwnerViewModel(application, filter) as T)
}