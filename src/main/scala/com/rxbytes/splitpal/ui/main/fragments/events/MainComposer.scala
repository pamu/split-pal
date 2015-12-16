package com.rxbytes.splitpal.ui.main.fragments.events

import com.rxbytes.splitpal.{TR, TypedFindView}

/**
  * Created by pnagarjuna on 16/12/15.
  */
trait MainComposer {

  self: TypedFindView =>

  lazy val content = Option(findView(TR.content))

  lazy val listView = Option(findView(TR.list_view))

  lazy val fabActionButton = Option(findView(TR.fab_action_button))

}
