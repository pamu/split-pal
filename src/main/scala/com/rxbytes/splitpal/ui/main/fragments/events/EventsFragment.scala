package com.rxbytes.splitpal.ui.main.fragments.events

import java.util.Date

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view._
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.main.fragments.events.layouts.EventsLayout
import macroid.{ContextWrapper, Contexts}
import macroid.FullDsl._

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
    val eLayout = layout(fragmentContextWrapper)
    runUi(init())

    runUi(adapter(new EventsListRecyclerAdapter(Seq.fill[Event](1)(
      Event(1, "Party", "", new Date(), None, 1, 1, 1)
    ))(event => Unit)))

    eLayout
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}
