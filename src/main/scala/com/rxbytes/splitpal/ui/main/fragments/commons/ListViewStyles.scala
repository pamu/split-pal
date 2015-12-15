package com.rxbytes.splitpal.ui.main.fragments.commons

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.{Button, TextView, LinearLayout, ProgressBar}
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 14/12/15.
  */
trait ListViewStyles {

  def progressBarStyle(implicit contextWrapper: ContextWrapper): Tweak[ProgressBar] =
    vWrapContent +
      Tweak[ProgressBar](_.setIndeterminate(true)) +
      flLayoutGravity(Gravity.CENTER) +
      vGone

  def recyclerListStyle(implicit contextWrapper: ContextWrapper): Tweak[RecyclerView] =
    llWrapWeightHorizontal

  def contentStyle(implicit contextWrapper: ContextWrapper) =
    flMatchWeightVertical

  def placeholderContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      llGravity(Gravity.CENTER) +
      vGone

  def msgStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    vWrapContent +
      tvSizeResource(R.dimen.status_font) +
      tvColorResource(R.color.colorPrimary) +
      llLayoutGravity(Gravity.CENTER) +
      tvGravity(Gravity.CENTER)

  def btnStyle(implicit contextWrapper: ContextWrapper): Tweak[Button] =
    vWrapContent +
      llLayoutGravity(Gravity.CENTER) +
      tvText("Reload")

  def recyclerHolderStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      vMatchWidth +
      vVisible

  def fastScrollStyle(implicit contextWrapper: ContextWrapper): Tweak[VerticalRecyclerViewFastScroller] =
    lp[LinearLayout](20 dp, MATCH_PARENT) +
      vMargin(0 dp, 0 dp, 5 dp, 0 dp) +
      Tweak[VerticalRecyclerViewFastScroller](_.setBarColor(resGetColor(R.color.colorPrimary))) +
      Tweak[VerticalRecyclerViewFastScroller](_.setHandleColor(resGetColor(R.color.colorPrimaryDark))) +
      Tweak[VerticalRecyclerViewFastScroller](_.setScrollbarFadingEnabled(true)) +
      Tweak[VerticalRecyclerViewFastScroller](_.setScrollBarFadeDuration(1000))

}
