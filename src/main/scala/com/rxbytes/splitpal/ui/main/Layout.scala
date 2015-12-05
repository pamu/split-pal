package com.rxbytes.splitpal.ui.main

import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import com.rxbytes.splitpal.ui.commons.{SlidingTabLayoutStyles, SlidingTabLayout}
import macroid.{IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Layout
  extends Styles
  with SlidingTabLayoutStyles
  with IdGeneration {

  var slidingTabLayout = slot[SlidingTabLayout]

  var viewPager = slot[ViewPager]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[LinearLayout](
      l[SlidingTabLayout]() <~ wire(slidingTabLayout) <~ slidingTabLayoutStyle,
      l[ViewPager]() <~ wire(viewPager) <~ viewPagerStyle <~ id(Id.pager)
    ) <~ contentStyle
  )

}
