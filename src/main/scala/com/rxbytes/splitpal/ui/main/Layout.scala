package com.rxbytes.splitpal.ui.main

import android.support.v4.view.ViewPager
import android.widget.FrameLayout
import macroid.{IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Layout
  extends Styles
  with IdGeneration {

  var viewPager = slot[ViewPager]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[FrameLayout](
      w[ViewPager] <~ wire(viewPager) <~ viewPagerStyle <~ id(Id.pager)
    ) <~ contentStyle
  )

}
