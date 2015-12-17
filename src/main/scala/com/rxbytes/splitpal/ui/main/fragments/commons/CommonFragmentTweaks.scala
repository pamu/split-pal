package com.rxbytes.splitpal.ui.main.fragments.commons

import android.graphics.Color
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.components.IconTypes._
import com.rxbytes.splitpal.ui.components.PathMorphDrawable
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 17/12/15.
  */
trait CommonFragmentTweaks {

  def fabDrawable(implicit contextWrapper: ContextWrapper) = new PathMorphDrawable(
    defaultIcon = ADD,
    defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
    defaultColor = Color.WHITE
  )

}
