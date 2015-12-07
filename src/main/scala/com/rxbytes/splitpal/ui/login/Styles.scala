package com.rxbytes.splitpal.ui.login

import macroid.ActivityContextWrapper
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._

/**
  * Created by pnagarjuna on 04/12/15.
  */
trait Styles {

  def contentStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    vMatchParent +
      llVertical

}
