package com.example.coco.view.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coco.dataStore.MyDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class IntroViewModel : ViewModel() {

    private val _first = MutableLiveData<Boolean>()
    val first : LiveData<Boolean>
        get() = _first

    fun checkFirstFlag() = viewModelScope.launch {

        delay(900)

         val getData = MyDataStore().getFirstData()

        _first.value = getData

        Timber.d(_first.value.toString())
    }


}