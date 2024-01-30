package com.example.coco.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.coco.DB.entity.InterestCoinEntity
import com.example.coco.dataModel.UpDownDataSet
import com.example.coco.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

	private val dbRepo = DBRepository()
	lateinit var selectedCoinList: LiveData<List<InterestCoinEntity>>

	private val _arr15 = MutableLiveData<List<UpDownDataSet>>()
	val arr15 : LiveData<List<UpDownDataSet>> get() = _arr15

	private val _arr30 = MutableLiveData<List<UpDownDataSet>>()
	val arr30 : LiveData<List<UpDownDataSet>> get() = _arr30

	private val _arr45 = MutableLiveData<List<UpDownDataSet>>()
	val arr45 : LiveData<List<UpDownDataSet>> get() = _arr45

	// CoinListFragment

	fun getAllInterestCoinData() = viewModelScope.launch {
		val coinList = dbRepo.getAllInterestCoinData().asLiveData()
		selectedCoinList = coinList

	}

	fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) =
		viewModelScope.launch(Dispatchers.IO) {

			interestCoinEntity.selected = !interestCoinEntity.selected
			dbRepo.updateInterestCoinData(interestCoinEntity)
		}

	// PriceChangeFragment

	fun getAllSelectedCoinData() = viewModelScope.launch(Dispatchers.IO) {
		// 1. 관심있는 코인 리스트 가져오기
		val selectedCoinList = dbRepo.getAllSelectedCoinData()

		val arr15 = ArrayList<UpDownDataSet>()
		val arr30 = ArrayList<UpDownDataSet>()
		val arr45 = ArrayList<UpDownDataSet>()

		// 2. 관심있는 코인 리스트 반복문으로 하나씩 가져오기
		for (data in selectedCoinList) {
			val coinName = data.coin_name // 관심있는 코인 이름 ex) BTC
			val oneCoinData =
				dbRepo.getDetailCoinData(coinName).reversed() // [ BTC15, BTC30, BTC45 ]
			val size = oneCoinData.size

			if (size > 1) {
				val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[1].price.toDouble()
				val upDownDataSet = UpDownDataSet(
					coinName,
					changedPrice.toString()
				)
				arr15.add(upDownDataSet)
			}

			if (size > 2) {
				val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[2].price.toDouble()
				val upDownDataSet = UpDownDataSet(
					coinName,
					changedPrice.toString()
				)
				arr30.add(upDownDataSet)
			}

			if (size > 3) {
				val changedPrice = oneCoinData[0].price.toDouble() - oneCoinData[3].price.toDouble()
				val upDownDataSet = UpDownDataSet(
					coinName,
					changedPrice.toString()
				)
				arr45.add(upDownDataSet)
			}

		}

		// background에서 실행하면 안되기 떄문에 main스레드에서 실행
		withContext(Dispatchers.Main){
			_arr15.value = arr15
			_arr30.value = arr30
			_arr45.value = arr45
		}


	}

}