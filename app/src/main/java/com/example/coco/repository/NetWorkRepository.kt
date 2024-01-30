package com.example.coco.repository

import com.example.coco.network.Api
import com.example.coco.network.RetrofitInstance

// network >> api를 호출해서 사용
class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getInterestCoinPriceData(coin: String) = client.getRecentCoinPirce(coin)


}