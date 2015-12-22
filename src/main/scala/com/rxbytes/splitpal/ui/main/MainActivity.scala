package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.{MenuItem, Menu}
import com.rxbytes.splitpal.ui.commons.SlidingTabLayout
import com.rxbytes.splitpal.ui.commons.SlidingTabLayout.TabColorizer
import com.rxbytes.splitpal.{R, TypedFindView}
import macroid.{Tweak, Contexts}
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.ViewPagerTweaks._
import scala.language.postfixOps
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class MainActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with TypedFindView
  with MainComposer
  with ScrollDirectionListener {

  val LOG_TAG = classOf[MainActivity].getSimpleName

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_layout)

    toolbar map setSupportActionBar

    val adapter = new SplitPalPageAdapter(getSupportFragmentManager)
    runUi(pager <~ vpAdapter(adapter) <~ vpOffscreenPageLimit(3))

    runUi {
      (slidingTabLayout <~ Tweak[SlidingTabLayout](_.setDistributeEvenly(true))) ~
        (slidingTabLayout <~ Tweak[SlidingTabLayout](_.setCustomTabColorizer(new TabColorizer {
          override def getIndicatorColor(position: Int): Int = resGetColor(R.color.white)
        }))) ~
        (slidingTabLayout <~ Tweak[SlidingTabLayout](_.setViewPager(pager.get))) ~
        (slidingTabLayout <~ Tweak[SlidingTabLayout](_.setOnPageChangeListener(new OnPageChangeListener {

          override def onPageScrollStateChanged(i: Int): Unit = {}

          override def onPageScrolled(i: Int, v: Float, i1: Int): Unit = {}

          override def onPageSelected(i: Int): Unit = {
            Log.d(LOG_TAG, s"page selected $i")
          }

        })))
    }

  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = super.onCreateOptionsMenu(menu)

  override def onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

  override def onScrollUp(): Unit = {}

  override def onScrollDown(): Unit = {}

}

trait ScrollDirectionListener {

  def onScrollUp(): Unit

  def onScrollDown(): Unit

}
