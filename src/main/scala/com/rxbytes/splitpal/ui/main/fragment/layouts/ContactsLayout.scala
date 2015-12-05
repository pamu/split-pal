package com.rxbytes.splitpal.ui.main.fragment.layouts

import android.widget.{LinearLayout, TextView}
import com.rxbytes.splitpal.ui.main.fragment.styles.ContactsStyle
import macroid.ContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsLayout(implicit contextWrapper: ContextWrapper)
  extends ContactsStyle {

  var title = slot[TextView]

  val content = getUi(
    l[LinearLayout](
      w[TextView] <~ wire(title) <~ textStyle
    ) <~ contactsContentStyle
  )

  def layout = content

}
