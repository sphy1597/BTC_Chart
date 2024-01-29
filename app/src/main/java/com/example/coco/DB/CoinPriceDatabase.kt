package com.example.coco.DB

import android.content.Context
import androidx.room.*
import com.example.coco.DB.dao.InterestCoinDAO
import com.example.coco.DB.dao.SelectedCoinPriceDAO
import com.example.coco.DB.entity.DataConverters
import com.example.coco.DB.entity.InterestCoinEntity
import com.example.coco.DB.entity.SelectedCoinPriceEntity


@Database(entities = [InterestCoinEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DataConverters::class)
abstract class CoinPriceDatabase : RoomDatabase() {

    abstract fun interestCoinDAO() : InterestCoinDAO
    abstract fun selectedCoinDAO() : SelectedCoinPriceDAO

    companion object {
        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(
            context: Context
        ) : CoinPriceDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}