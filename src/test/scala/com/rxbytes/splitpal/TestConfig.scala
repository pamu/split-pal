package com.rxbytes.splitpal

import android.content.Context
import org.specs2.mock.Mockito

trait TestConfig extends Mockito {

  val mockContext = mock[Context]

}
