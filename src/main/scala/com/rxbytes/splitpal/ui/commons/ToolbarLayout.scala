package com.rxbytes.splitpal.ui.commons

import android.support.v7.widget.Toolbar
import android.view.{ContextThemeWrapper, View}
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, Ui}

/**
  * Created by pnagarjuna on 05/12/15.
  */

trait ToolbarLayout extends ToolbarStyles {

  var toolBar = slot[Toolbar]

  def toolBarLayout(children: Ui[View]*)(implicit contextWrapper: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      val darkToolBar = getToolbarThemeDarkActionBar
      children foreach (uiView => darkToolBar.addView(uiView.get))
      toolBar = Some(darkToolBar)
      darkToolBar
    } <~ toolbarStyle(resGetDimensionPixelSize(R.dimen.height_toolbar))

  def expandedToolBarLayout(children: Ui[View]*)
                           (height: Int)
                           (implicit contextWrapper: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      val darkToolBar = getToolbarThemeDarkActionBar
      children foreach (uiView => darkToolBar.addView(uiView.get))
      toolBar = Some(darkToolBar)
      darkToolBar
    } <~ toolbarStyle(height)


  private def getToolbarThemeDarkActionBar(implicit contextWrapper: ActivityContextWrapper) = {
    val contextTheme = new ContextThemeWrapper(contextWrapper.application, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
    val darkToolBar = new Toolbar(contextTheme)
    darkToolBar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
    darkToolBar
  }

}