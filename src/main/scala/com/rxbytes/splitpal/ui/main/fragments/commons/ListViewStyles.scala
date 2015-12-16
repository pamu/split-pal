package com.rxbytes.splitpal.ui.main.fragments.commons

import android.support.design.widget.{CoordinatorLayout, FloatingActionButton}
import android.view.Gravity
import android.widget._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 14/12/15.
  */
trait ListViewStyles {

  def progressBarStyle(implicit contextWrapper: ContextWrapper): Tweak[ProgressBar] =
    vWrapContent +
      Tweak[ProgressBar](_.setIndeterminate(true)) +
      flLayoutGravity(Gravity.CENTER) +
      vGone

  def listStyle(implicit contextWrapper: ContextWrapper): Tweak[ListView] =
    vMatchParent

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

  def listHolderStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      vMatchParent +
      vVisible

}
