package com.rxbytes.splitpal.ui.main.fragments.events

import java.util.Date

import org.ocpsoft.prettytime.PrettyTime

/**
  * Created by pnagarjuna on 13/12/15.
  */
case class Event(id: Int,
                 name: String,
                 description: String,
                 creationTime: Date,
                 eventPhoto: Option[String],
                 moneyInvolved: Int,
                 payment: Int,
                 peopleInvolved: Int) {

  def prettyCreatedAt: String = new PrettyTime().format(creationTime)

  def moneyInvolvedText: String = s"total $moneyInvolved bugs"

  def paymentText: String = if (payment > 0) s"you get $payment" else s"you owe $payment"

  def peopleInvolvedText: String = s"$peopleInvolved people"

  def paymentPeopleText: String = "3 owe you"

}
