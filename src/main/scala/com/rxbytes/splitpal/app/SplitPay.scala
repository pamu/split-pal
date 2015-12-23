package com.rxbytes.splitpal.app

import android.app.Application
import macroid.Contexts

/**
  * Created by pnagarjuna on 23/12/15.
  */
class SplitPay
  extends Application
  with Contexts[Application] {

  override def onCreate(): Unit = {
    super.onCreate()
  }

}
