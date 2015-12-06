package com.rxbytes.splitpal.ui.main.fragments.contacts.styles

import android.view.Gravity
import android.widget.TextView
import macroid.{Tweak, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ContactsStyle {

  def textStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    vMatchParent +
    tvGravity(Gravity.CENTER) +
    llLayoutGravity(Gravity.CENTER)

  def contactsContentStyle(implicit contextWrapper: ContextWrapper) =
    vMatchParent +
    llVertical

}
