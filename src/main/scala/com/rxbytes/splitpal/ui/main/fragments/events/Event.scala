package com.rxbytes.splitpal.ui.main.fragments.events

import java.util.Date

/**
  * Created by pnagarjuna on 13/12/15.
  */
case class Event(id: Int,
                 name: String,
                 description: String,
                 datetime: Date,
                 eventPhoto: Option[String],
                 totalFlow: Int,
                 flow: Int,
                 people: Int)
