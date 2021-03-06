package com.rxbytes.splitpal.ui.login

import android.widget.{TextView, LinearLayout}
import com.fortysevendeg.macroid.extras.TextTweaks._
import macroid.ActivityContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 04/12/15.
  */
trait Layout
  extends Styles {

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[LinearLayout](
      w[TextView] <~ tvText("Hello")
    ) <~ contentStyle
  )

}
