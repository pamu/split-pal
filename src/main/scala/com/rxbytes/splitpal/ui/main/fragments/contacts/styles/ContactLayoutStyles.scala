package com.rxbytes.splitpal.ui.main.fragments.contacts.styles

import android.graphics.Color
import android.support.v7.widget.CardView
import android.text.TextUtils.TruncateAt
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.ImageView.ScaleType
import android.widget.{AbsListView, TextView, LinearLayout, ImageView}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.R
import macroid.FullDsl._
import macroid.{Tweak, ContextWrapper}
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait ContactLayoutStyles {

  def cardStyle(implicit contextWrapper: ContextWrapper): Tweak[CardView] =
    lp[AbsListView](MATCH_PARENT, WRAP_CONTENT)

  def profilePicStyle(implicit contextWrapper: ContextWrapper): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(8 dp, 8 dp, 24 dp, 8 dp)
  }

  def profileNameStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvSizeResource(R.dimen.title_font) +
      tvColor(Color.BLACK) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END) +
      tvBold

  def profileStatusStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvColor(Color.GRAY) +
      llLayoutMargin(0,
        resGetDimensionPixelSize(R.dimen.padding_small),
        resGetDimensionPixelSize(R.dimen.padding_tiny),
        resGetDimensionPixelSize(R.dimen.padding_small)) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def profileContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      llWrapWeightHorizontal

  def contactContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    vMatchWidth +
      llGravity(Gravity.CENTER) +
      llHorizontal

  //  def lineStyle(implicit contextWrapper: ContextWrapper): Tweak[ImageView] =
  //    lp[LinearLayout](MATCH_PARENT, resGetDimensionPixelSize(R.dimen.line)) +
  //      vBackgroundColorResource(R.color.colorPrimary)

}
