package com.rxbytes.splitpal.ui.main.fragments.contacts.layouts

import android.support.v7.widget.RecyclerView
import android.widget.{FrameLayout, TextView}
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactsStyle
import macroid.ContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsLayout(implicit contextWrapper: ContextWrapper)
  extends ContactsStyle {

  var title = slot[TextView]
  var contactsList = slot[RecyclerView]

  val content = getUi(
    l[FrameLayout](
      w[TextView] <~ wire(title) <~ textStyle,
      w[RecyclerView] <~ wire(contactsList) <~ contactsListStyle
    ) <~ contactsContentStyle
  )

  def layout = content

}
