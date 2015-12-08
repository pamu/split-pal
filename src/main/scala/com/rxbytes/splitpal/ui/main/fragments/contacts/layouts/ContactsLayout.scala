package com.rxbytes.splitpal.ui.main.fragments.contacts.layouts

import android.support.v7.widget.RecyclerView
import android.widget._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactsStyle
import macroid.{Ui, ContextWrapper}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsLayout(implicit contextWrapper: ContextWrapper)
  extends ContactsStyle {

  var progressBar = slot[ProgressBar]
  var contactsList = slot[RecyclerView]
  var placeholder = slot[LinearLayout]
  var msg = slot[TextView]
  var reloadBtn = slot[Button]

  val content = getUi(
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      w[RecyclerView] <~ wire(contactsList) <~ contactsListStyle,
      l[LinearLayout](
        w[TextView] <~ wire(msg) <~ msgStyle,
        w[Button] <~ wire(reloadBtn) <~ btnStyle
      ) <~ wire(placeholder) <~ placeholderContentStyle
    ) <~ contactsContentStyle
  )

  def layout = content

  def loading(): Ui[_] =
    (progressBar <~ vVisible) ~
      (contactsList <~ vGone) ~
      (placeholder <~ vGone)

  def failed(): Ui[_] =
    (progressBar <~ vGone) ~
      (contactsList <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.error)) ~
      (reloadBtn <~ vVisible)

  def empty(): Ui[_] =
    (progressBar <~ vGone) ~
      (contactsList <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.empty)) ~
      (reloadBtn <~ vGone)

  def adapter[VH <: RecyclerView.ViewHolder](adapter: RecyclerView.Adapter[VH]): Ui[_] =
    (progressBar <~ vGone) ~
      (placeholder <~ vGone) ~
      (contactsList <~ vVisible) ~
      (contactsList <~ rvAdapter(adapter))

}
