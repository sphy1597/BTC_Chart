package com.example.coco.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.coco.DB.entity.InterestCoinEntity
import com.example.coco.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

	private val dbRepo = DBRepository()
	lateinit var selectedCoinList: LiveData<List<InterestCoinEntity>>

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

}