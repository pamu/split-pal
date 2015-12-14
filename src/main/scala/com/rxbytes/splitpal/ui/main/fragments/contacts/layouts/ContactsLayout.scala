package com.rxbytes.splitpal.ui.main.fragments.contacts.layouts

import android.util.Log
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.main.fragments.commons.ListViewLayout
import com.rxbytes.splitpal.ui.main.fragments.contacts.{ContactsListRecyclerAdapter, ContactsUtils}
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactsStyle
import macroid.{ActivityContextWrapper, Ui}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ContactsLayout
  extends ListViewLayout
  with ContactsStyle {

  self: ContextWrapperProvider =>

  def fetchContacts(implicit activityContextWrapper: ActivityContextWrapper): Ui[_] = {
    ContactsUtils.contactsAsync mapUi {
      contacts =>
        Log.d("contacts", s"${contacts.length}")
        reloadList(new ContactsListRecyclerAdapter(contacts)(contact => Unit))
    } recoverUi {
      case ex =>
        Log.d("failed", ex.getMessage)
        ex.printStackTrace()
        failed()
    }
    loading()
  }

}
