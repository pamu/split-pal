package com.rxbytes.splitpal.ui.main.fragments.events

import android.support.v7.widget.CardView
import android.widget.{ImageView, TextView, LinearLayout}
import com.rxbytes.splitpal.ui.main.fragments.events.styles.EventLayoutStyles
import macroid.{IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 13/12/15.
  */
class EventsLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends EventLayoutStyles with IdGeneration {

  var eventImage = slot[ImageView]
  var eventName = slot[TextView]
  var eventDesc = slot[TextView]
  var eventMoneyInvolved = slot[TextView]
  var eventPeopleInvolved = slot[TextView]
  var eventPayment = slot[TextView]
  var eventCreatedAt = slot[TextView]
  var paymentIcon = slot[ImageView]

  def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(eventImage) <~ eventImageStyle,
        l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ wire(eventName) <~ eventNameStyle <~ id(Id.eventName),
            w[TextView] <~ wire(eventCreatedAt) <~ eventCreatedAtStyle
          ) <~ titleStyle,
          l[LinearLayout](
            w[TextView] <~ wire(eventMoneyInvolved) <~ eventMoneyInvolvedStyle,
            w[TextView] <~ wire(eventPeopleInvolved) <~ eventPeopleInvolvedStyle
          ) <~ moneyPeopleStyle,
          l[LinearLayout](
            w[ImageView] <~ wire(paymentIcon) <~ paymentIconStyle,
            w[TextView] <~ wire(eventPayment) <~ eventPaymentStyle
          ) <~ paymentStyle
        ) <~ eventContentStyle
      ) <~ eventStyle
    ) <~ cardStyle
  )

  val content = layout

}
