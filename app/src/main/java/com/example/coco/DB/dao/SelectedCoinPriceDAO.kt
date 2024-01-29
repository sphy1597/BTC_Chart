package com.example.coco.DB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coco.DB.entity.SelectedCoinPriceEntity

@Dao
interface SelectedCoinPriceDAO {


	// get All Data
	@Query("SELECT * FROM selected_coin_price_table")
	fun getAllData(): List<SelectedCoinPriceEntity>

	// insert
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(selectedCoinPriceEntity: SelectedCoinPriceEntity)

	// get detail info
	@Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
	fun getDetailData(coinName: String): List<SelectedCoinPriceEntity>


}