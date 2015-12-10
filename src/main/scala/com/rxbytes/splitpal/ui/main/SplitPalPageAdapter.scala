package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.app.{FragmentStatePagerAdapter, FragmentManager, Fragment}
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.{Fragments, ContactsFragment}
import com.rxbytes.splitpal.ui.main.fragments.events.EventsFragment
import com.rxbytes.splitpal.ui.main.fragments.flows.FlowsFragment
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 05/12/15.
  */
class SplitPalPageAdapter(fragmentManager: FragmentManager)(implicit contextWrapper: ContextWrapper)
  extends FragmentStatePagerAdapter(fragmentManager) {

  val screens = Screens.screens(contextWrapper)

  override def getItem(i: Int): Fragment = i match {
    case 0 =>
      val fragment = new FlowsFragment
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, i)
      fragment.setArguments(args)
      fragment
    case 1 =>
      val fragment = new EventsFragment
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, i)
      fragment.setArguments(args)
      fragment
    case 2 =>
      val fragment = new ContactsFragment
      val args = new Bundle()
      args.putInt(Fragments.fragmentId, i)
      fragment.setArguments(args)
      fragment
  }

  override def getCount: Int = screens.length

  override def getPageTitle(position: Int): CharSequence = screens(position).title
}

object Screens {

  def screens(implicit contextWrapper: ContextWrapper) = List(
    Screen(contextWrapper.application.getString(R.string.flows)),
    Screen(contextWrapper.application.getString(R.string.events)),
    Screen(contextWrapper.application.getString(R.string.contacts))
  )

}

case class Screen(title: String)
