package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.app.{FragmentStatePagerAdapter, FragmentManager, Fragment}
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.{Fragments, ContactsFragment}
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 05/12/15.
  */
class SplitPalPageAdapter(fragmentManager: FragmentManager)(implicit contextWrapper: ContextWrapper)
  extends FragmentStatePagerAdapter(fragmentManager) {

  val screens = Screens.screens

  override def getItem(i: Int): Fragment = {
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
    Screen(contextWrapper.application.getString(R.string.screen_1)),
    Screen(contextWrapper.application.getString(R.string.screen_2)),
    Screen(contextWrapper.application.getString(R.string.contacts))
  )

}

case class Screen(title: String)
