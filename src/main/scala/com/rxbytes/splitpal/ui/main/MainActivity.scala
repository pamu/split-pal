package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
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

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(layout)

    val adapter = new SplitPalPageAdapter(getSupportFragmentManager)

    runUi(viewPager <~ vpAdapter(adapter))

    runUi(viewPager <~ vpOnPageChangeListener(new ViewPager.OnPageChangeListener {

      override def onPageScrollStateChanged(i: Int): Unit = {}

      override def onPageScrolled(i: Int, v: Float, i1: Int): Unit = {
        runUi(toast(s"i value scrolled $i") <~ fry)
      }

      override def onPageSelected(i: Int): Unit = {
        runUi(toast(s"i value selected $i") <~ fry)
      }

    }))

    slidingTabLayout.map(_.setDistributeEvenly(true))

    slidingTabLayout.map(_.setCustomTabColorizer(new TabColorizer {
      override def getIndicatorColor(position: Int): Int = getResources.getColor(R.color.colorPrimary)
    }))

    slidingTabLayout.map(_.setViewPager(viewPager.get))

  }
}
