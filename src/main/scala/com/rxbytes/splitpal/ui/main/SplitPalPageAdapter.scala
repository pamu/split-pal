package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.app.{FragmentStatePagerAdapter, FragmentManager, Fragment}
import android.util.Log
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.{Fragments, ContactsFragment}
import com.rxbytes.splitpal.ui.main.fragments.events.EventsFragment
import com.rxbytes.splitpal.ui.main.fragments.payments.{PaymentsFragment, FlowsFragment}
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 05/12/15.
  */
class SplitPalPageAdapter(fragmentManager: FragmentManager)(implicit contextWrapper: ContextWrapper)
  extends FragmentStatePagerAdapter(fragmentManager) {

  val LOG_TAG = classOf[SplitPalPageAdapter].getSimpleName

  val screens = Screens.screens(contextWrapper)
  val payments = new PaymentsFragment
  val events = new EventsFragment
  val contacts = new ContactsFragment

  Log.d(LOG_TAG, "SplitPalPageAdapter created")

  override def getItem(i: Int): Fragment = i match {
    case 0 =>
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, 0)
      payments.setArguments(args)
      payments
    case 1 =>
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, 1)
      events.setArguments(args)
      events
    case 2 =>
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, 2)
      contacts.setArguments(args)
      contacts
  }

  override def getCount: Int = screens.length

  override def getPageTitle(position: Int): CharSequence = screens(position).title


}

object Screens {

  def screens(implicit contextWrapper: ContextWrapper) = List(
    Screen(contextWrapper.application.getString(R.string.flows)),
    Screen(contextWrapper.application.getString(R.string.events)),
    Screen(contextWrapper.application.getString(R.string.contacts)))

}

case class Screen(title: String)
