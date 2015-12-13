package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.commons.ContextWrapperProvider
//import com.rxbytes.splitpal.ui.main.Screens
import com.rxbytes.splitpal.ui.main.fragments.contacts.layouts.ContactsLayout
import macroid.{ContextWrapper, Contexts}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsFragment
  extends Fragment
  with Contexts[Fragment]
  with ContextWrapperProvider
  with ContactsLayout {

  override lazy implicit val contextProvider: ContextWrapper = fragmentContextWrapper

  val LOG_TAG = classOf[ContactsFragment].getSimpleName()

  Log.d(LOG_TAG, "Contacts Fragment created")

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
//    val screens = Screens.screens(fragmentContextWrapper)
//    val element = getArguments.getInt(Fragments.fragmentId)
//    val screen = screens(element)

    //call the layout before doing any operation on it.
    val contactsLayout = layout
    runUi(init() ~ fetchContacts())
    contactsLayout
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    inflater.inflate(R.menu.main_menu, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}

object Fragments {
  val fragmentId = "fragment_id"
}