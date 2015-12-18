package com.rxbytes.splitpal.ui.main.fragments.payments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view._
import com.rxbytes.splitpal.ui.main.fragments.commons.CommonFragmentTweaks
import com.rxbytes.splitpal.{TR, TypedResource}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import macroid.{Ui, Contexts}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 16/12/15.
  */
class PaymentsFragment
  extends Fragment
  with Contexts[Fragment]
  with CommonFragmentTweaks {

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {

    implicit val rootView = TypedResource.TypedLayoutInflater(inflater).inflate(TR.layout.material_list, container)

    runUi {
      fabActionButton <~ ivSrc(super.fabDrawable) <~ On.click {
        Ui {
          fabActionButton foreach (Snackbar.make(_, "Add New Payment", Snackbar.LENGTH_LONG).show())
        }
      }
    }

    rootView
  }

  override def onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Unit = {
    super.onCreateOptionsMenu(menu, inflater)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    super.onOptionsItemSelected(item)
  }

}
