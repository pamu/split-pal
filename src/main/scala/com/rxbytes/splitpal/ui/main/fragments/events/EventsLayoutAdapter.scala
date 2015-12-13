package com.rxbytes.splitpal.ui.main.fragments.events

import android.widget.{TextView, LinearLayout}
import com.rxbytes.splitpal.ui.main.fragments.events.styles.EventLayoutStyles
import macroid.ActivityContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 13/12/15.
  */
class EventsLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends EventLayoutStyles {

  var eventName = slot[TextView]

  def layout = getUi(
    l[LinearLayout](
      w[TextView] <~ wire(eventName) <~ eventStyle
    ) <~ eventContentStyle
  )

  val content = layout

}
