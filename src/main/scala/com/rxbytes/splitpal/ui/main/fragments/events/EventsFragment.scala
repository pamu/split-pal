package com.rxbytes.splitpal.ui.main.fragments.events

import java.util.Date

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.ui.main.fragments.commons.CommonFragmentTweaks
import com.rxbytes.splitpal.ui.main.fragments.commons.ListViewTweaks._
import com.rxbytes.splitpal.{R, TypedResource, TR}
import macroid.{Ui, Contexts}
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

    val view = TypedResource.TypedLayoutInflater(inflater).inflate(TR.layout.material_list, container)
    val progressBar = Option(TypedResource.TypedView(view).findView(TR.progress_bar))
    val placeholder = Option(TypedResource.TypedView(view).findView(TR.placeholder))
    val listView = Option(TypedResource.TypedView(view).findView(TR.list_view))
    runUi {
      listView <~ lvAdapter(new EventsListAdapter(Seq.fill[Event](10)(
        Event(1, "Party", "", new Date(), None, 1, 1, 1)
      ))(event => Unit))
    }
    val fabActionButton = Option(TypedResource.TypedView(view).findView(TR.fab_action_button))
    runUi(fabActionButton <~ ivSrc(super.fabDrawable) <~ On.click {
      Ui {
        fabActionButton.foreach(Snackbar.make(_, "Add new Event", Snackbar.LENGTH_LONG).show())
      }
    })

    def init: Ui[_] = {
      (progressBar <~ vGone) ~
        (placeholder <~ vVisible)
    }

    def loading: Ui[_] = {
      (progressBar <~ vVisible) ~
        (placeholder <~ vGone)
    }


    view
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}
