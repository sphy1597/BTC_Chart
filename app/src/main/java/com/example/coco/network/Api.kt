package com.example.coco.network

import com.example.coco.network.model.CurrentPriceList
import retrofit2.http.GET

interface Api {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList


}