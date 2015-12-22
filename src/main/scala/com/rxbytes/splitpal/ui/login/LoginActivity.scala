package com.rxbytes.splitpal.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rxbytes.splitpal.ui.main.MainActivity
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
    finish()
    startActivity(new Intent(getApplication, classOf[MainActivity]))
  }

}
