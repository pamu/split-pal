package com.rxbytes.splitpal.ui.main.fragments.commons

import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import android.widget.BaseAdapter
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.rxbytes.splitpal.{TR, TypedResource, R}
import com.rxbytes.splitpal.ui.components.IconTypes._
import com.rxbytes.splitpal.ui.components.PathMorphDrawable
import macroid.{Contexts, Ui, ContextWrapper}
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

  def error(implicit rootView: View): Ui[_] = {
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

}
