package com.rxbytes.splitpal.ui.main

import android.support.v4.view.ViewPager
import android.widget.LinearLayout
import com.rxbytes.splitpal.ui.commons.{ToolbarLayout, SlidingTabLayoutStyles, SlidingTabLayout}
import macroid.{IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Layout
  extends Styles
  with ToolbarLayout
  with SlidingTabLayoutStyles
  with IdGeneration {

  var slidingTabLayout = slot[SlidingTabLayout]

  var viewPager = slot[ViewPager]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[LinearLayout](
      toolBarLayout,
      w[SlidingTabLayout]() <~ wire(slidingTabLayout) <~ slidingTabLayoutStyle,
      w[ViewPager]() <~ wire(viewPager) <~ viewPagerStyle <~ id(Id.pager)
    ) <~ contentStyle
  )

}
