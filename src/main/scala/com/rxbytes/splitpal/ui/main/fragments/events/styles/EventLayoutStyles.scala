package com.rxbytes.splitpal.ui.main.fragments.events.styles

import android.text.TextUtils.TruncateAt
import android.view.Gravity
import android.widget.ImageView.ScaleType
import android.widget.{AbsListView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import macroid.FullDsl._
import macroid.{Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import android.view.ViewGroup.LayoutParams._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventLayoutStyles {

  def eventImageStyle(implicit contextWrapper: ContextWrapper) = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      ivSrc(R.drawable.ic_launcher) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(8 dp, 8 dp, 24 dp, 8 dp)
  }

  def eventStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      llGravity(Gravity.CENTER)

  def eventNameStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvColorResource(R.color.colorPrimary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END) +
      tvBold


  def eventContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      llWrapWeightHorizontal

  def cardStyle(implicit contextWrapper: ContextWrapper) =
    lp[AbsListView](MATCH_PARENT, WRAP_CONTENT)

}
