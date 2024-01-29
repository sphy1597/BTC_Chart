package com.example.coco.repository

import com.example.coco.App
import com.example.coco.DB.CoinPriceDatabase
import com.example.coco.DB.entity.InterestCoinEntity
import com.example.coco.DB.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)

    // InterestCoin

    // 전체 코인 데이터
    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()

    // 코인 데이터 넣기
    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity)

    // 코인 데이터 업데이트
    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity)


    // 사용자가 관심있는 코인만 가져오기
    fun getAllSelectedCoinData() = db.interestCoinDAO().getSelectedData()

    // CoinPrice
    fun getAllCoinPriceData() = db.selectedCoinDAO().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) = db.selectedCoinDAO().insert(selectedCoinPriceEntity)

    fun getDetailCoinData(coinName : String) = db.selectedCoinDAO().getDetailData(coinName)
}