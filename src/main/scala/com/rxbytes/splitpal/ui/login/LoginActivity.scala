package com.rxbytes.splitpal.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import macroid.{IdGeneration, Contexts}

/**
  * Created by pnagarjuna on 04/12/15.
  */
class LoginActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with IdGeneration
  with Layout {

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(layout)
  }

}
