package com.example.coco.network.model

import com.example.coco.dataModel.RecentPriceData

data class RecentPriceList(
	val status: String,
	val data : List<RecentPriceData>,
)