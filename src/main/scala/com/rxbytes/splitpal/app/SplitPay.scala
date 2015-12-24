package com.rxbytes.splitpal.app

import android.app.Application
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.parse.Parse
import com.rxbytes.splitpal.R
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 23/12/15.
  */
class SplitPay
  extends Application {

  override def onCreate(): Unit = {
    super.onCreate()

    implicit val contextWrapper = ContextWrapper(this)

    Parse.initialize(getApplicationContext,
      resGetString(R.string.parse_app_id),
      resGetString(R.string.parse_client_key))

  }

}
