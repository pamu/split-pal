package com.rxbytes.splitpal.ui.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.{View, ViewGroup, LayoutInflater}
import com.rxbytes.splitpal.ui.main.Screens
import com.rxbytes.splitpal.ui.main.fragment.layouts.ContactsLayout
import macroid.Contexts
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.TextTweaks._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsFragment
  extends Fragment
  with Contexts[Fragment] {

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val screens = Screens.screens
    val element = getArguments.getInt(Fragments.fragmentId)
    val screen = screens(element)
    val cLayout = new ContactsLayout
    runUi(cLayout.title <~ tvText(screen.title))
    cLayout.layout
  }

}

object Fragments {
  val fragmentId = "fragment_id"
}