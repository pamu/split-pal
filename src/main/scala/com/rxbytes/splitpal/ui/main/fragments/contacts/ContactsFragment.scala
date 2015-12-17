package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.graphics.{PorterDuff, Color}
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view._
import android.widget.ProgressBar
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.ui.main.fragments.commons.CommonFragmentTweaks
import com.rxbytes.splitpal.{TR, TypedResource, R}
import macroid.{Tweak, Ui, Contexts}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsFragment
  extends Fragment
  with Contexts[Fragment]
  with CommonFragmentTweaks {

  val LOG_TAG = classOf[ContactsFragment].getSimpleName()

  Log.d(LOG_TAG, "Contacts Fragment created")

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    /**
    val screens = Screens.screens(fragmentContextWrapper)
    val element = getArguments.getInt(Fragments.fragmentId)
    val screen = screens(element) **/

    /**
    val contactsLayout = layout
    runUi(init ~ fetchContacts(fragmentContextWrapper))
    contactsLayout **/

    val view = TypedResource.TypedLayoutInflater(inflater).inflate(TR.layout.material_list, container)
    val progressBar = Option(TypedResource.TypedView(view).findView(TR.progress_bar))
    val placeholder = Option(TypedResource.TypedView(view).findView(TR.placeholder))
    val listView = Option(TypedResource.TypedView(view).findView(TR.list_view))

    def init: Ui[_] = {
      (progressBar <~ vGone) ~
        (placeholder <~ vVisible) ~
        (listView <~ vVisible)
    }

    def loading: Ui[_] = {
      (progressBar <~ vVisible) ~
        (progressBar <~ Tweak[ProgressBar](_.getProgressDrawable.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN))) ~
        (placeholder <~ vVisible) ~
        (listView <~ vGone)
    }

    runUi(loading)

    val fabActionButton = Option(TypedResource.TypedView(view).findView(TR.fab_action_button))
    runUi(fabActionButton <~ ivSrc(super.fabDrawable) <~ On.click {
      Ui {
        fabActionButton.foreach(Snackbar.make(_, "Add new Contact", Snackbar.LENGTH_LONG).show())
      }
    })

    view
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