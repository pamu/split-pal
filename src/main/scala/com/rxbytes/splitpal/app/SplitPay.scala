package com.rxbytes.splitpal.app

import android.app.Application
import com.parse.Parse
import macroid.Contexts

/**
  * Created by pnagarjuna on 23/12/15.
  */
class SplitPay
  extends Application
  with Contexts[Application] {

  override def onCreate(): Unit = {
    super.onCreate()
    Parse.initialize(
      this,
      "4VjpLzQc4L7qtDsPo3fJJ0eCxvvxNfIPkHie5dJd",
      "4lgF7JLo3wPQYsn3oeSK0MnzOnQedwiYtvgozqYO")
  }

}
