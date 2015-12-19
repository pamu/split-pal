package com.rxbytes.splitpal.ui.main.fragments.events.styles

import android.graphics.Color
import android.text.TextUtils.TruncateAt
import android.view.Gravity
import android.widget.ImageView.ScaleType
import android.widget.{AbsListView, LinearLayout, TextView}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import macroid.FullDsl._
import macroid.{IdGeneration, Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import android.view.ViewGroup.LayoutParams._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventLayoutStyles extends IdGeneration {

  def eventImageStyle(implicit contextWrapper: ContextWrapper) = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      ivSrc(R.drawable.user_placeholder) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(8 dp, 8 dp, 24 dp, 8 dp)
  }

  def eventStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      llGravity(Gravity.CENTER)

  def eventNameStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llWrapWeightHorizontal +
      vMargin(marginLeft = 0 dp, marginTop = 0 dp, marginBottom = 4 dp, marginRight = 0 dp) +
      tvColor(Color.BLACK) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END) +
      tvSizeResource(R.dimen.title_font) +
      tvBold


  def eventContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      llWrapWeightHorizontal +
      vMargin(0 dp, 0 dp, 8 dp, 0 dp)

  def cardStyle(implicit contextWrapper: ContextWrapper) =
    lp[AbsListView](MATCH_PARENT, WRAP_CONTENT)

  def eventCreatedAtStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    tvColor(Color.GRAY) +
      tvSizeResource(R.dimen.status_font)

  def titleStyle(implicit contextWrapper: ContextWrapper) =
    vMatchWidth +
      llHorizontal +
      llLayoutMargin(0 dp, 8 dp, 0 dp, 4 dp)

  def moneyIconStyle(implicit contextWrapper: ContextWrapper) = {
    val size = resGetDimensionPixelSize(R.dimen.payment_type_icon_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      ivSrc(R.drawable.user_placeholder) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(0 dp, 0 dp, 4 dp, 0 dp)
  }

  def eventMoneyInvolvedStyle(implicit contextWrapper: ContextWrapper) =
    llWrapWeightHorizontal

  def moneyStyle(implicit contextWrapper: ContextWrapper) =
    llHorizontal +
      vWrapContent +
      llLayoutMargin(0 dp, 0 dp, 8 dp, 0 dp)

//  def verticalLineStyle(implicit contextWrapper: ContextWrapper): Tweak[ImageView] =
//      lp[LinearLayout](resGetDimensionPixelSize(R.dimen.line), MATCH_PARENT) +
//        vBackgroundColorResource(R.color.colorPrimary)

  def peopleIconStyle(implicit contextWrapper: ContextWrapper) = {
    val size = resGetDimensionPixelSize(R.dimen.payment_type_icon_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      ivSrc(R.drawable.user_placeholder) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(0 dp, 0 dp, 4 dp, 0 dp)
  }

  def eventPeopleInvolvedStyle(implicit contextWrapper: ContextWrapper) =
    llWrapWeightHorizontal

  def peopleStyle(implicit contextWrapper: ContextWrapper) =
    llHorizontal +
      vWrapContent

  def moneyPeopleStyle(implicit contextWrapper: ContextWrapper) =
    vMatchWidth +
      llHorizontal +
      llLayoutMargin(0 dp, 0 dp, 0 dp, 8 dp)

  def paymentIconStyle(implicit contextWrapper: ContextWrapper) = {
    val size = resGetDimensionPixelSize(R.dimen.payment_type_icon_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      ivSrc(R.drawable.user_placeholder) +
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(0 dp, 0 dp, 8 dp, 0 dp)
  }

  def eventPaymentStyle(implicit contextWrapper: ContextWrapper) =
    llWrapWeightHorizontal

  def paymentStyle(implicit contextWrapper: ContextWrapper) =
    vMatchWidth +
      llHorizontal +
      llLayoutMargin(0 dp, 0 dp, 0 dp, 8 dp)

  def eventDescriptionStyle(implicit contextWrapper: ContextWrapper) =
    vMatchWidth +
      tvMaxLines(3) +
      tvEllipsize(TruncateAt.END) +
      llLayoutMargin(0 dp, 0 dp, 0 dp, 8 dp)

}
