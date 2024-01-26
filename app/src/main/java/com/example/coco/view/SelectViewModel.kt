package com.example.coco.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coco.dataModel.CurrentPrice
import com.example.coco.dataModel.CurrentPriceResult
import com.example.coco.dataStore.MyDataStore
import com.example.coco.repository.NetWorkRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

// SelectActivity에서 사용할 기능들
class SelectViewModel : ViewModel() {

    private val netWorkRepository = NetWorkRepository()

    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    // 데이터 변화 관찰 LiveData 사용
    private val _currentPriceResult = MutableLiveData<List<CurrentPriceResult>>()
    val currentPriceResult : LiveData<List<CurrentPriceResult>>
            get() = _currentPriceResult


    fun getCurrentCoinList() = viewModelScope.launch {
        val result = netWorkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()

        for (coin in result.data){

            try{
                val gson = Gson()
                val gsonToJSon = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJSon, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)
                currentPriceResultList.add(currentPriceResult)

            }catch (e: java.lang.Exception){
                Timber.d(e.toString())
            }

        }
        // 전체 코인 데이터를 받아와서 리스트에 뿌려줌
        _currentPriceResult.value = currentPriceResultList

    }

    fun setUpFirstFlat() = viewModelScope.launch {
        MyDataStore().setupFirstData()
    }


    // DB에 데이터 저장
    // Room과는 Dispatchers.io를 쓰는게 좋다고 공식 문서에 있음
    fun saveSelectedCoinList(selectedCoinList: ArrayList<String>) = viewModelScope.launch (Dispatchers.IO){
        // 1. 전체 코인 데이터 가져오기
        for(coin in currentPriceResultList){
            Timber.d(coin.toString())
            
            val selected = selectedCoinList.contains(coin.coinName)
        }

        // 2. 내가 선택한 코인인지 아닌지 구분하기

        // 3. 저장
    }




}