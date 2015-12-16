package com.rxbytes.splitpal.ui.main.fragments.commons

import android.view.View
import android.widget.{ListAdapter, ListView}
import macroid.Tweak

/**
  * Created by pnagarjuna on 16/12/15.
  */
object ListViewTweaks {
  type W = ListView

  def lvAdapter[LA <: ListAdapter](adapter: LA): Tweak[W] =
    Tweak[W](_.setAdapter(adapter))

  def lvHeaderView[V <: View](headerView: View): Tweak[W] =
    Tweak[W](_.addHeaderView(headerView))

  def lvEnableFastScroll: Tweak[W] =
    Tweak[W](_.setFastScrollEnabled(true))

  def lvVisibleFastScroll: Tweak[W] =
    Tweak[W](_.setFastScrollAlwaysVisible(true))

  def lvHideDivider: Tweak[W] =
    Tweak[W](_.setDivider(null)) +
      Tweak[W](_.setDividerHeight(0))

}
