package com.rxbytes.splitpal.ui.main.fragments.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view._
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.main.fragments.events.layouts.EventsLayout
import macroid.{ContextWrapper, Contexts}

/**
  * Created by pnagarjuna on 07/12/15.
  */
class EventsFragment
  extends Fragment
  with Contexts[Fragment]
  with ContextWrapperProvider
  with EventsLayout {

  override lazy implicit val contextProvider: ContextWrapper = fragmentContextWrapper

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val eLayout = layout(contextProvider)
    eLayout
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }
  
}
