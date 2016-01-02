package com.rxbytes.splitpal.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
  * Created by pnagarjuna on 01/01/16.
  */
class SyncService extends Service {

  private val LOG_TAG = classOf[SyncService].getSimpleName

  override def onBind(intent: Intent): IBinder = null

  override def onStartCommand(intent: Intent, flags: Int, startId: Int): Int = {
    Log.d(LOG_TAG, "onStartCommand SyncService")
    super.onStartCommand(intent, flags, startId)
  }

}
