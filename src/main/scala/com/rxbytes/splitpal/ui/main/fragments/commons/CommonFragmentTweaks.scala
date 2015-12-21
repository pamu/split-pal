package com.rxbytes.splitpal.ui.main.fragments.commons

import android.graphics.Color
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.View
import android.widget.AbsListView.OnScrollListener
import android.widget.{AbsListView, ListView, BaseAdapter}
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.rxbytes.splitpal.{TR, TypedResource, R}
import com.rxbytes.splitpal.ui.components.IconTypes._
import com.rxbytes.splitpal.ui.components.PathMorphDrawable
import macroid.{Tweak, Contexts, Ui, ContextWrapper}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 17/12/15.
  */
trait CommonFragmentTweaks {

  self: Contexts[Fragment] =>

  def fabDrawable(implicit contextWrapper: ContextWrapper) = new PathMorphDrawable(
    defaultIcon = ADD,
    defaultStroke = resGetDimensionPixelSize(R.dimen.circular_reveal_fab_stroke),
    defaultColor = Color.WHITE
  )

  def fabActionButton(implicit rootView: View) = {
    val fabActionButton = Option(TypedResource.TypedView(rootView).findView(TR.fab_action_button))
    fabActionButton
  }

  def listView(implicit rootView: View) = {
    val listView = Option(TypedResource.TypedView(rootView).findView(TR.list_view))
    runUi(listView <~ ListViewTweaks.lvEnableFastScroll)
    listView
  }

  def progressBar(implicit rootView: View) = {
    val progressBar = Option(TypedResource.TypedView(rootView).findView(TR.progress_bar))
    progressBar
  }

  def btn(implicit rootView: View) = {
    val btn = Option(TypedResource.TypedView(rootView).findView(TR.btn))
    btn
  }

  def msg(implicit rootView: View) = {
    val msg = Option(TypedResource.TypedView(rootView).findView(TR.msg))
    msg
  }

  def notifPlaceholder(implicit rootView: View) = {
    val notifPlaceholder = Option(TypedResource.TypedView(rootView).findView(TR.notif_placeholder))
    notifPlaceholder
  }

  def placeholder(implicit rootView: View) = {
    val placeholder = Option(TypedResource.TypedView(rootView).findView(TR.placeholder))
    placeholder
  }


  def init(implicit rootView: View): Ui[_] = {
    (listView <~ vVisible) ~
      hideFabAfter(1) ~
      (progressBar <~ vGone) ~
      (notifPlaceholder <~ vGone)
  }

  def loading(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (progressBar <~ vVisible) ~
      (notifPlaceholder <~ vGone)
  }

  def adapter[BA <: BaseAdapter](baseAdapter: BaseAdapter)(implicit rootView: View): Ui[_] = {
    (listView <~ vVisible) ~
      (listView <~ ListViewTweaks.lvAdapter(baseAdapter)) ~
      (progressBar <~ vGone) ~
      (notifPlaceholder <~ vGone)
  }

  def empty(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (progressBar <~ vGone) ~
      (notifPlaceholder <~ vVisible) ~
      (btn <~ vGone) ~
      (msg <~ tvText(resGetString(R.string.empty)))
  }

  def failed(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (progressBar <~ vGone) ~
      (notifPlaceholder <~ vVisible) ~
      (btn <~ vVisible) ~
      (msg <~ tvText(resGetString(R.string.error)))
  }

  def reloadList[BA <: BaseAdapter](baseAdapter: BaseAdapter)(implicit rootView: View): Ui[_] =
    baseAdapter.getCount match {
      case 0 => empty
      case _ => adapter(baseAdapter)
    }

  def hideFabAfter(itemCount: Int)(implicit rootView: View): Ui[_] =
    listView <~ Tweak[ListView](_.setOnScrollListener(new OnScrollListener {

      var lastVisibleItemPosition = listView.map(_.getFirstVisiblePosition).getOrElse(0)
      var isScrollingUp = false

      override def onScrollStateChanged(absListView: AbsListView, i: Int): Unit = {
        if (isScrollingUp) {
          runUi {
            fabActionButton <~ Tweak[FloatingActionButton](_.show())
          }
        } else {
          runUi {
            fabActionButton <~ Tweak[FloatingActionButton](_.hide())
          }
        }
      }

      override def onScroll(absListView: AbsListView, i: Int, i1: Int, i2: Int): Unit = {
        if (lastVisibleItemPosition > i) {
          isScrollingUp = true
        } else if (lastVisibleItemPosition < i){
          isScrollingUp = false
        }

        lastVisibleItemPosition = i
      }

    }))

}
