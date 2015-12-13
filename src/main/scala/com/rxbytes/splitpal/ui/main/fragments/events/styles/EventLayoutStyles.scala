package com.rxbytes.splitpal.ui.main.fragments.events.styles

import macroid.ContextWrapper
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventLayoutStyles {

  def eventStyle(implicit contextWrapper: ContextWrapper) =
    vWrapContent

  def eventContentStyle(implicit contextWrapper: ContextWrapper) =
    llVertical

}
