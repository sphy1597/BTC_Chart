package com.example.coco.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.coco.view.main.MainActivity
import timber.log.Timber

// 앱 종료시에도 사용 가능한 서비스
class PriceForegroundService : Service() {

	private val NOTIFICATION_ID = 10000

	override fun onCreate() {
		super.onCreate()
	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

		when(intent?.action){
			"START" -> {
				Timber.tag("테스트").d("START")
			}

			"STOP" -> {
				Timber.tag("테스트").d("STOP")
			}

		}


		return START_STICKY
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}

	suspend fun makeNotification() : Notification {
		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
		}

		val pendingIntent : PendingIntent = PendingIntent.getActivity(
			this,
			0,
			intent,
			PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
		)


		return
	}


}