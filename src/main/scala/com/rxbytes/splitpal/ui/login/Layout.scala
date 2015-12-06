package com.rxbytes.splitpal.ui.login

import android.widget.{TextView, LinearLayout}
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.rxbytes.splitpal.ui.commons.ToolbarLayout
import macroid.ActivityContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 04/12/15.
  */
trait Layout
  extends Styles
  with ToolbarLayout {

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[LinearLayout](
      toolBarLayout(),
      w[TextView] <~ tvText("Hello")
    )
  )

}
