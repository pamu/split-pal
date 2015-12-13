package com.rxbytes.splitpal.ui.main.fragments.contacts.layouts

import android.support.v7.widget.{LinearLayoutManager, GridLayoutManager, RecyclerView}
import android.util.Log
import android.widget._
import com.fortysevendeg.macroid.extras.DeviceMediaQueries._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.commons.ListItemDecorator
import com.rxbytes.splitpal.ui.main.fragments.contacts.{ContactsListRecyclerAdapter, Contact, ContactsUtils}
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactsStyle
import macroid.{ActivityContextWrapper, Ui}
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ContactsLayout
  extends ContactsStyle {

  self: ContextWrapperProvider =>

  var progressBar = slot[ProgressBar]
  var contactsList = slot[RecyclerView]
  var placeholder = slot[LinearLayout]
  var msg = slot[TextView]
  var reloadBtn = slot[Button]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      w[RecyclerView] <~ wire(contactsList) <~ contactsListStyle,
      l[LinearLayout](
        w[TextView] <~ wire(msg) <~ msgStyle,
        w[Button] <~ On.click {
          fetchContacts
        } <~ wire(reloadBtn) <~ btnStyle
      ) <~ wire(placeholder) <~ placeholderContentStyle
    ) <~ contactsContentStyle
  )

  def layoutManager(implicit activityContextWrapper: ActivityContextWrapper) =
    landscapeTablet ?
      new GridLayoutManager(activityContextWrapper.application, 3) |
      tablet ?
        new GridLayoutManager(activityContextWrapper.application, 2) | new LinearLayoutManager(activityContextWrapper.application)

  def init()(implicit activityContextWrapper: ActivityContextWrapper): Ui[_] =
    (contactsList <~ vVisible) ~
      (contactsList <~
        rvLayoutManager(layoutManager) <~
        rvAddItemDecoration(new ListItemDecorator)) ~
      (placeholder <~ vGone) ~
      (progressBar <~ vGone)

  def loading(): Ui[_] = {
    Log.d("loading", "loa")
    (progressBar <~ vVisible) ~
      (contactsList <~ vGone) ~
      (placeholder <~ vGone)
  }

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

  def adapter[VH <: RecyclerView.ViewHolder](adapter: RecyclerView.Adapter[VH])(implicit activityContextWrapper: ActivityContextWrapper): Ui[_] =
    (progressBar <~ vGone) ~
      (placeholder <~ vGone) ~
      (contactsList <~ vVisible) ~
      (contactsList <~ rvLayoutManager(layoutManager)
        <~ rvAddItemDecoration(new ListItemDecorator)
        <~ rvAdapter(adapter))

  def reloadList[VH <: RecyclerView.ViewHolder]
  (adapterInstance: RecyclerView.Adapter[VH])
  (implicit activityContextWrapper: ActivityContextWrapper): Ui[_] = adapterInstance.getItemCount match {
    case 0 => empty()
    case _ => adapter(adapterInstance)
  }

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
