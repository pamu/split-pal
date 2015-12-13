package com.rxbytes.splitpal.ui.main.fragments.events.styles

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.{Button, TextView, LinearLayout, ProgressBar}
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventsStyles {

  def progressBarStyle(implicit contextWrapper: ContextWrapper): Tweak[ProgressBar] =
    vWrapContent +
      Tweak[ProgressBar](_.setIndeterminate(true)) +
      flLayoutGravity(Gravity.CENTER) +
      vGone

  def eventsListStyle(implicit contextWrapper: ContextWrapper): Tweak[RecyclerView] =
    vMatchParent +
      vVisible

  def eventsContentStyle(implicit contextWrapper: ContextWrapper) =
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

}
