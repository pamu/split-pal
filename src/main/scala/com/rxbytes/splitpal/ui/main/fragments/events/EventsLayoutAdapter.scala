package com.rxbytes.splitpal.ui.main.fragments.events

import android.support.v7.widget.CardView
import android.widget.{ImageView, TextView, LinearLayout}
import com.rxbytes.splitpal.ui.main.fragments.events.styles.EventLayoutStyles
import macroid.ActivityContextWrapper
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 13/12/15.
  */
class EventsLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends EventLayoutStyles {

  var eventName = slot[TextView]
  var eventImage = slot[ImageView]

  def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(eventImage) <~ eventImageStyle,
        l[LinearLayout](
          w[TextView] <~ wire(eventName) <~ eventNameStyle
        ) <~ eventContentStyle
      ) <~ eventStyle
    ) <~ cardStyle
  )

  val content = layout

}
