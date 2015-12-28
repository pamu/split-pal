package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.rxbytes.splitpal.ui.main.fragments.commons.CommonFragmentComposer
import com.rxbytes.splitpal.{TR, TypedResource, R}
import macroid.{Ui, Contexts}
import macroid.FullDsl._
import com.rxbytes.splitpal.ui.main.fragments.contacts.ContactsFetcher._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class ContactsFragment
  extends Fragment
  with Contexts[Fragment]
  with CommonFragmentComposer {

  val LOG_TAG = classOf[ContactsFragment].getSimpleName()

  Log.d(LOG_TAG, "Contacts Fragment created")

  val limit = 10

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    /**
    val screens = Screens.screens(fragmentContextWrapper)
    val element = getArguments.getInt(Fragments.fragmentId)
    val screen = screens(element) **/

    /**
    val contactsLayout = layout
    runUi(init ~ fetchContacts(fragmentContextWrapper))
    contactsLayout **/

    setHasOptionsMenu(true)

    implicit val view = TypedResource.TypedLayoutInflater(inflater).inflate(TR.layout.fragment_layout, container, false)
    runUi(fabActionButton <~ ivSrc(super.fabDrawable) <~ On.click {
      Ui {
        fabActionButton.foreach(Snackbar.make(_, "Add new Contact", Snackbar.LENGTH_LONG).show())
      }
    })
    runUi(init ~ fetchPage(limit, 0))
    view
  }

  var currentPage = 0

  def fetchPage(limit: Int, offset: Int)(implicit rootView: View): Ui[_] = {
    contactsAsync(limit, offset) mapUi { contacts =>
      reloadList(new ContactsListAdapter(contacts)(contact => Unit))
    } recoverUi { case ex =>
      Log.e(LOG_TAG, ex.getMessage)
      ex.printStackTrace()
      failed
    }
    loading
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    inflater.inflate(R.menu.contacts_menu, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}

object Fragments {
  val fragmentId = "fragment_id"
}