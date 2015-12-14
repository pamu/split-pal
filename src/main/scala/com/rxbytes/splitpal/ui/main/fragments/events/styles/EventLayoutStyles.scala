package com.rxbytes.splitpal.ui.main.fragments.events.styles

import android.view.Gravity
import android.widget.TextView
import macroid.{Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventLayoutStyles {

  def eventStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      tvGravity(Gravity.CENTER) +
      llLayoutGravity(Gravity.CENTER) +
      tvBold


  def eventContentStyle(implicit contextWrapper: ContextWrapper) =
    llVertical

  def cardStyle(implicit contextWrapper: ContextWrapper) =
    vMatchWidth

}
