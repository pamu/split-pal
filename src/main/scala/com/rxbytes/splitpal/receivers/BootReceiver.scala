package com.rxbytes.splitpal.receivers

import android.content.{Intent, Context, BroadcastReceiver}

/**
  * Created by pnagarjuna on 15/12/15.
  */
class BootReceiver extends BroadcastReceiver {
  override def onReceive(context: Context, intent: Intent): Unit = {
    if (intent.getAction == Intent.ACTION_BOOT_COMPLETED) {

    }
  }
}
