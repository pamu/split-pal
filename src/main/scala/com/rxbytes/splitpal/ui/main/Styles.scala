package com.rxbytes.splitpal.ui.main

import macroid.ActivityContextWrapper
import com.fortysevendeg.macroid.extras.ViewTweaks._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Styles {

  def viewPagerStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    vMatchParent

  def contentStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    vMatchParent

}
