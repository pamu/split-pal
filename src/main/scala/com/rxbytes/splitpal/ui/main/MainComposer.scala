package com.rxbytes.splitpal.ui.main

import com.rxbytes.splitpal.{TR, TypedFindView}

/**
  * Created by pnagarjuna on 22/12/15.
  */
trait MainComposer {
  self: TypedFindView =>

  lazy val appBarLayout = Option(findView(TR.app_bar_layout))
  lazy val toolbar = Option(findView(TR.toolbar))
  lazy val slidingTabLayout = Option(findView(TR.sliding_tab_layout))
  lazy val pager = Option(findView(TR.pager))


}
