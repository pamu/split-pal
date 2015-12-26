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

import com.rxbytes.splitpal.ui.main.ScrollDirectionListener
import com.rxbytes.splitpal.{TR, TypedResource, R}
import com.rxbytes.splitpal.ui.components.IconTypes._
import com.rxbytes.splitpal.ui.components.PathMorphDrawable
import macroid._
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 17/12/15.
  */
trait CommonFragmentComposer extends IdGeneration {

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

  def listViewContainer(implicit rootView: View) = {
    val listViewContainer = Option(TypedResource.TypedView(rootView).findView(TR.list_view_container))
    listViewContainer
  }

  def listViewProgressWheel(implicit rootView: View) = {
    val listViewProgressWheel = Option(TypedResource.TypedView(rootView).findView(TR.list_view_progress_wheel))
    listViewProgressWheel
  }

  def mainProgressWheel(implicit rootView: View) = {
    val mainProgressWheel = Option(TypedResource.TypedView(rootView).findView(TR.main_progress_wheel))
    mainProgressWheel
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
      keepActivityPosted ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vGone)
  }

  def loading(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (mainProgressWheel <~ vVisible) ~
      (notifPlaceholder <~ vGone)
  }

  def adapter[BA <: BaseAdapter](baseAdapter: BaseAdapter)(implicit rootView: View): Ui[_] = {
    (listView <~ vVisible) ~
      (listView <~ ListViewTweaks.lvAdapter(baseAdapter)) ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vGone)
  }

  def empty(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vVisible) ~
      (btn <~ vGone) ~
      (msg <~ tvText(resGetString(R.string.empty)))
  }

  def failed(implicit rootView: View): Ui[_] = {
    (listView <~ vGone) ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vVisible) ~
      (btn <~ vVisible) ~
      (msg <~ tvText(resGetString(R.string.error)))
  }

  def reloadList[BA <: BaseAdapter](baseAdapter: BaseAdapter)(implicit rootView: View): Ui[_] =
    baseAdapter.getCount match {
      case 0 => empty
      case _ => adapter(baseAdapter)
    }

  def pageLoading(implicit rootView: View): Ui[_] = {
    (listView <~ vVisible) ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vGone) ~
      (listViewProgressWheel <~ vVisible)
  }

  def pageLoadingDone(implicit rootView: View): Ui[_] = {
    (listView <~ vVisible) ~
      (mainProgressWheel <~ vGone) ~
      (notifPlaceholder <~ vGone) ~
      (listViewProgressWheel <~ vGone)
  }

  def onScroll(up: => Unit)(down: => Unit)(implicit rootView: View): Ui[_] =
    listView <~ Tweak[ListView](_.setOnScrollListener(new OnScrollListener {

      var lastVisibleItemPosition = listView.map(_.getFirstVisiblePosition).getOrElse(0)
      var isScrollingUp = false

      override def onScrollStateChanged(absListView: AbsListView, i: Int): Unit =
        if (isScrollingUp) up else down

      override def onScroll(absListView: AbsListView, i: Int, i1: Int, i2: Int): Unit = {
        if (lastVisibleItemPosition > i) {
          isScrollingUp = true
        } else if (lastVisibleItemPosition < i) {
          isScrollingUp = false
        }
        lastVisibleItemPosition = i
      }

    }))

  def keepActivityPosted(implicit rootView: View): Ui[_] = {
    fragmentContextWrapper.original.get.map {
      activity =>
        onScroll {
          runUi {
            fabActionButton <~ Tweak[FloatingActionButton](_.show())
          }
          activity.asInstanceOf[ScrollDirectionListener].onScrollUp()
        } {
          runUi {
            fabActionButton <~ Tweak[FloatingActionButton](_.hide())
          }
          activity.asInstanceOf[ScrollDirectionListener].onScrollDown()
        }
    }.getOrElse(Ui())
  }

}
