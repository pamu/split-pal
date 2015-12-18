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
  var eventDescription = slot[TextView]
  var eventMoneyInvolved = slot[TextView]
  var eventPeopleInvolved = slot[TextView]
  var eventPayment = slot[TextView]
  var eventCreatedAt = slot[TextView]
  var paymentIcon = slot[ImageView]

  def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(eventImage) <~ eventImageStyle <~ id(Id.eventImage),
        l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ wire(eventName) <~ eventNameStyle <~ id(Id.eventName),
            w[TextView] <~ wire(eventCreatedAt) <~ eventCreatedAtStyle <~ id(Id.eventCreatedAt)
          ) <~ titleStyle,
          w[TextView] <~ wire(eventDescription) <~ eventDescriptionStyle <~ id(Id.eventDescription),
          l[LinearLayout](
            w[TextView] <~ wire(eventMoneyInvolved) <~ eventMoneyInvolvedStyle <~ id(Id.eventMoneyInvolved),
            w[TextView] <~ wire(eventPeopleInvolved) <~ eventPeopleInvolvedStyle <~ id(Id.eventPeopleInvolved)
          ) <~ moneyPeopleStyle,
          l[LinearLayout](
            w[ImageView] <~ wire(paymentIcon) <~ paymentIconStyle <~ id(Id.eventPaymentIcon),
            w[TextView] <~ wire(eventPayment) <~ eventPaymentStyle <~ id(Id.eventPayment)
          ) <~ paymentStyle
        ) <~ eventContentStyle
      ) <~ eventStyle
    ) <~ cardStyle
  )

  val content = layout

}
