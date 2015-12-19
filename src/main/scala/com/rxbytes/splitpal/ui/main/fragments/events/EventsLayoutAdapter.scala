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

  def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ eventImageStyle <~ id(Id.eventImage),
        l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ eventNameStyle <~ id(Id.eventName),
            w[TextView] <~ eventCreatedAtStyle <~ id(Id.eventCreatedAt)
          ) <~ titleStyle,
          w[TextView] <~ eventDescriptionStyle <~ id(Id.eventDescription),
          l[LinearLayout](
            l[LinearLayout](
              w[ImageView] <~ moneyIconStyle <~ id(Id.moneyIcon),
              w[TextView] <~ eventMoneyInvolvedStyle <~ id(Id.eventMoneyInvolved)
            ) <~ moneyStyle,
            l[LinearLayout](
              w[ImageView] <~ peopleIconStyle <~ id(Id.peopleIcon),
              w[TextView] <~ eventPeopleInvolvedStyle <~ id(Id.eventPeopleInvolved)
            ) <~ peopleStyle
          ) <~ moneyPeopleStyle,
          l[LinearLayout](
            w[ImageView] <~ paymentIconStyle <~ id(Id.eventPaymentIcon),
            w[TextView] <~ eventPaymentStyle <~ id(Id.eventPayment)
          ) <~ paymentStyle
        ) <~ eventContentStyle
      ) <~ eventStyle
    ) <~ cardStyle
  )

  val content = layout

}
