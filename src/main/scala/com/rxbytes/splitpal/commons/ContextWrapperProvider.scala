package com.rxbytes.splitpal.commons

import macroid.ContextWrapper

trait ContextWrapperProvider {

  implicit val contextProvider : ContextWrapper

}
