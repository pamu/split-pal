package com.rxbytes.splitpal.ui.main.fragments.contacts.styles

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.{Button, TextView, LinearLayout, ProgressBar}
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ContactsStyle {

  def progressBarStyle(implicit contextWrapper: ContextWrapper): Tweak[ProgressBar] =
    vWrapContent +
      Tweak[ProgressBar](_.setIndeterminate(true)) +
      flLayoutGravity(Gravity.CENTER) +
      vGone

  def contactsListStyle(implicit contextWrapper: ContextWrapper): Tweak[RecyclerView] =
    vMatchParent +
      vVisible

  def contactsContentStyle(implicit contextWrapper: ContextWrapper) =
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
