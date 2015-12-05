package com.rxbytes.splitpal.modules.parse

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ParseServices {
  def save: Unit
}

trait ParseServicesComponent {
  val parseServices: ParseServices
}