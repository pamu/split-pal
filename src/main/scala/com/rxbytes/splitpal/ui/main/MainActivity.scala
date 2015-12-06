package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.{MenuItem, Menu}
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.commons.SlidingTabLayout.TabColorizer
import macroid.Contexts
import com.fortysevendeg.macroid.extras.ViewPagerTweaks._
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class MainActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with Layout {

  val LOG_TAG = classOf[MainActivity].getSimpleName

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(layout)

    val adapter = new SplitPalPageAdapter(getSupportFragmentManager)

    runUi(viewPager <~ vpAdapter(adapter))

    slidingTabLayout.map(_.setDistributeEvenly(true))

    slidingTabLayout.map(_.setCustomTabColorizer(new TabColorizer {
      override def getIndicatorColor(position: Int): Int = getResources.getColor(R.color.colorPrimary)
    }))

    slidingTabLayout.map(_.setViewPager(viewPager.get))

    slidingTabLayout.map(_.setOnPageChangeListener(new ViewPager.OnPageChangeListener {

      override def onPageScrollStateChanged(i: Int): Unit = {}

      override def onPageScrolled(i: Int, v: Float, i1: Int): Unit = {}

      override def onPageSelected(i: Int): Unit = {
        Log.d(LOG_TAG, s"page selected ${i}")
      }

    }))
    
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = super.onCreateOptionsMenu(menu)

  override def onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

}
