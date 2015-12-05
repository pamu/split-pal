package com.rxbytes.splitpal.modules.parse.impl

import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.modules.parse.{ParseServices, ParseServicesComponent}

/**
  * Created by pnagarjuna on 05/12/15.
  */
trait ParseServicesComponentImpl
  extends ParseServicesComponent {

  self: ContextWrapperProvider =>


  override val parseServices: ParseServices = new ParseServicesImpl

  class ParseServicesImpl extends ParseServices {
    override def save: Unit =  {}
  }

}
