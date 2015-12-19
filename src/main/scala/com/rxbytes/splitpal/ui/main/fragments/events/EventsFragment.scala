package com.rxbytes.splitpal.ui.main.fragments.events

import java.util.Date

import android.os.Bundle
import android.support.design.widget.{FloatingActionButton, Snackbar}
import android.support.v4.app.Fragment
import android.view._
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.rxbytes.splitpal.ui.main.fragments.commons.CommonFragmentTweaks
import com.rxbytes.splitpal.{R, TypedResource, TR}
import macroid.{Tweak, Ui, Contexts}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 07/12/15.
  */
class EventsFragment
  extends Fragment
  with Contexts[Fragment]
  with CommonFragmentTweaks {

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    /**
    val eLayout = layout(fragmentContextWrapper)
    runUi(init)
    runUi(adapter(new EventsListAdapter(Seq.fill[Event](10)(
      Event(1, "Party", "", new Date(), None, 1, 1, 1)
    ))(event => Unit)))
    eLayout **/

    setHasOptionsMenu(true)
    implicit val rootView = TypedResource.TypedLayoutInflater(inflater).inflate(TR.layout.material_list, container, false)

    runUi(init)

    runUi {
      val date = new Date()
      adapter(EventsListAdapter(Seq.fill[Event](10)(Event(1,
        "Tamasha Movie", "Tamasha movie at Urvashi Theatre", date, None, 1000, 750, 4))))
    }

    runUi {
      fabActionButton <~ ivSrc(super.fabDrawable) <~ On.click {
        Ui {
          fabActionButton.foreach(Snackbar.make(_, "Add new Event", Snackbar.LENGTH_LONG).show())
        }
      }
    }

    rootView
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    inflater.inflate(R.menu.events_menu, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}
