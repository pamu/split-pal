package com.rxbytes.splitpal.ui.commons

import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 05/12/15.
  */

trait ToolbarStyles {

  val toolbarStyle =
    vBackground(R.color.colorPrimary) +
      vMatchWidth

}

trait SlidingTabLayoutStyles {

  def slidingTabLayoutStyle(implicit context: ContextWrapper): Tweak[SlidingTabLayout] =
    vMatchWidth +
      vBackground(R.color.colorPrimary)

}

