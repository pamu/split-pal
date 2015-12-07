package com.rxbytes.splitpal.ui.main.fragments.contacts.styles

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.TextView
import com.rxbytes.splitpal.R
import macroid.{Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import macroid.FullDsl._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ContactsStyle {

  def textStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    vWrapContent +
      tvColor(R.color.colorPrimary) +
      tvBoldItalicCondensed +
      tvSize(8 dp) +
      tvGravity(Gravity.CENTER) +
      flLayoutGravity(Gravity.CENTER) +
      flLayoutGravity(Gravity.CENTER) +
      vGone

  def contactsListStyle(implicit contextWrapper: ContextWrapper): Tweak[RecyclerView] =
    vMatchParent +
      vVisible

  def contactsContentStyle(implicit contextWrapper: ContextWrapper) =
    flMatchWeightVertical

}
