package com.rxbytes.splitpal.ui.commons

import android.support.v7.widget.Toolbar
import android.view.ContextThemeWrapper
import com.rxbytes.splitpal.R
import macroid.FullDsl._
import macroid.{ActivityContextWrapper, Ui}

/**
  * Created by pnagarjuna on 05/12/15.
  */

trait ToolbarLayout extends ToolbarStyles {

  var toolBar = slot[Toolbar]

  def toolBarLayout(implicit context: ActivityContextWrapper): Ui[Toolbar] =
    Ui {
      val darkToolBar = getToolbarThemeDarkActionBar
      toolBar = Some(darkToolBar)
      darkToolBar
    } <~ toolbarStyle

  private def getToolbarThemeDarkActionBar(implicit context: ActivityContextWrapper) = {
    val contextTheme = new ContextThemeWrapper(context.getOriginal, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
    val darkToolBar = new Toolbar(contextTheme)
    darkToolBar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light)
    darkToolBar
  }

}