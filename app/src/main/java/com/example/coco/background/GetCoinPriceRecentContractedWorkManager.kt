package com.example.coco.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coco.DB.entity.SelectedCoinPriceEntity
import com.example.coco.network.model.RecentPriceList
import com.example.coco.repository.DBRepository
import com.example.coco.repository.NetWorkRepository
import timber.log.Timber
import java.util.*

// 최근 거래된 코인 가격 내역 가져오는 work manager

// 1. 관심있어하는 코인 리스트를 가져와서
// 2. 관심있는 코인 각각의 가격 변동 정보를 가져와서 (New API)
// 3. 관심있는 코인 각각 변동 정보 DB저장

class GetCoinPriceRecentContractedWorkManager(val context: Context, workerParameters: WorkerParameters)
	: CoroutineWorker(context, workerParameters){

	private val dbRepo = DBRepository()
	private val networkRepo = NetWorkRepository()

	override suspend fun doWork(): Result {


		Timber.d("doWork")


		return Result.success()
	}

	// 1. 관심있어하는 코인 리스트를 가져오기
	suspend fun getAllInterestSelectedCoinList(){
		val selectedCoinList = dbRepo.getAllSelectedCoinData()

		for( coinData in selectedCoinList){
			Timber.d(coinData.toString())

			// 2. 관심있는 코인 각각의 가격 변동 정보를 가져와서 (New API)
			val recentCoinPriceList  = networkRepo.getRecentCoinList(coinData.coin_name)

			Timber.d(recentCoinPriceList.toString())
			saveSelecCoinPrice(
				coinData.coin_name,
				recentCoinPriceList,

			)
		}

	}

	fun saveSelecCoinPrice(
		coinName : String,
		recentCoinPriceList: RecentPriceList,
		timeStamp : Date
	){
		val selectedCoinPriceList = SelectedCoinPriceEntity(
			0,
			coinName,
			recentCoinPriceList.data[0].transaction_data,
			recentCoinPriceList.data[0].type,
			recentCoinPriceList.data[0].units_traded,
			recentCoinPriceList.data[0].price,
			recentCoinPriceList.data[0].total

		)

	}


}