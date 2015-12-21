package com.rxbytes.splitpal.ui.main

import android.support.v4.view.ViewPager
import android.widget.{FrameLayout, LinearLayout}
import com.rxbytes.splitpal.ui.commons.{ToolbarLayout, SlidingTabLayoutStyles, SlidingTabLayout}
import macroid.{IdGeneration, ActivityContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait Layout
  extends Styles
  with ToolbarLayout
  with SlidingTabLayoutStyles
  with IdGeneration {

  var slidingTabLayout = slot[SlidingTabLayout]
  var toolbarContainer = slot[LinearLayout]
  var viewPager = slot[ViewPager]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[FrameLayout](
      w[ViewPager] <~ wire(viewPager) <~ viewPagerStyle <~ id(Id.pager),
      l[LinearLayout](
        toolBarLayout,
        w[SlidingTabLayout] <~ wire(slidingTabLayout) <~ slidingTabLayoutStyle
      ) <~ wire(toolbarContainer) <~ mainContentStyle
    ) <~ contentStyle
  )

  def initMainLayout(size: Int)(implicit activityContextWrapper: ActivityContextWrapper) = {
    viewPager <~ vPadding(
      0,
      size dp,//slidingTabLayout.map(_.getMeasuredHeight).getOrElse(0) + toolBar.map(_.getMeasuredHeight).getOrElse(0),
      0,
      0)
  }

}
