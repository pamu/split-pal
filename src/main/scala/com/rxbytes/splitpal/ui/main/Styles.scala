package com.rxbytes.splitpal.ui.main

import com.rxbytes.splitpal.ui.commons.ToolbarStyles
import macroid.ActivityContextWrapper
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Styles
  extends ToolbarStyles {

  def viewPagerStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    vMatchParent

  def contentStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    vMatchParent

  def mainContentStyle(implicit activityContextWrapper: ActivityContextWrapper) =
    llVertical +
      vMatchWidth

}
