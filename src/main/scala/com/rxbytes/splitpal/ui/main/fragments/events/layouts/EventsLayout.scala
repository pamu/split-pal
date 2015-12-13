package com.rxbytes.splitpal.ui.main.fragments.events.layouts

import android.support.v7.widget.RecyclerView
import android.widget._
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.main.fragments.events.styles.EventsStyles
import macroid.ContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 13/12/15.
  */
trait EventsLayout
  extends EventsStyles {

  var progressBar = slot[ProgressBar]
  var eventsList = slot[RecyclerView]
  var msg = slot[TextView]
  var reloadBtn = slot[Button]
  var placeholder = slot[LinearLayout]

  def layout(implicit contextWrapper: ContextWrapper) = getUi(
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      w[RecyclerView] <~ wire(eventsList) <~ eventsListStyle,
      l[LinearLayout](
        w[TextView] <~ wire(msg) <~ msgStyle,
        w[Button] <~ wire(reloadBtn) <~ btnStyle
      ) <~ wire(placeholder) <~ placeholderContentStyle
    ) <~ eventsContentStyle
  )

}
