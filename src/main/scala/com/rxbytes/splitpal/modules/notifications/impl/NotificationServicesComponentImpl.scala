package com.rxbytes.splitpal.modules.notifications.impl

import com.rxbytes.splitpal.modules.notifications.{NotificationServicesComponent, NotificationServices}

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait NotificationServicesComponentImpl
  extends NotificationServicesComponent {


  override val notificationServices: NotificationServices = new NotificationServicesImpl

  class NotificationServicesImpl
    extends NotificationServices {

  }

}
