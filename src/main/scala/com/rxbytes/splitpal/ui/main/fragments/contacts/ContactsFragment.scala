package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.Screens
import com.rxbytes.splitpal.ui.main.fragments.contacts.layouts.ContactsLayout
import macroid.Contexts
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsFragment
  extends Fragment
  with Contexts[Fragment] {

  val LOG_TAG = classOf[ContactsFragment].getSimpleName

  Log.d(LOG_TAG, "Contacts Fragment created")

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val screens = Screens.screens
    val element = getArguments.getInt(Fragments.fragmentId)
    val screen = screens(element)
    val cLayout = new ContactsLayout
    runUi(cLayout.title <~ tvText(screen.title))
    cLayout.layout
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