package com.rxbytes.splitpal.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.{MenuItem, Menu}
import macroid.Contexts
import com.fortysevendeg.macroid.extras.ViewPagerTweaks._
import macroid.FullDsl._

/**
  * Created by pnagarjuna on 05/12/15.
  */
class MainActivity
  extends AppCompatActivity
  with Contexts[AppCompatActivity]
  with Layout
  with ScrollDirectionListener {

  val LOG_TAG = classOf[MainActivity].getSimpleName

  protected override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(layout)
    val adapter = new SplitPalPageAdapter(getSupportFragmentManager)
    runUi(viewPager <~ vpAdapter(adapter) <~ vpOffscreenPageLimit(3))
    /**
    runOnUiThread(new Runnable {
      override def run(): Unit = {
        slidingTabLayout.map(_.setDistributeEvenly(true))
        slidingTabLayout.map(_.setCustomTabColorizer(new TabColorizer {
          override def getIndicatorColor(position: Int): Int = resGetColor(R.color.white)
        }))
        slidingTabLayout.map(_.setViewPager(viewPager.get))
        slidingTabLayout.map(_.setOnPageChangeListener(new ViewPager.OnPageChangeListener {

          override def onPageScrollStateChanged(i: Int): Unit = {}

          override def onPageScrolled(i: Int, v: Float, i1: Int): Unit = {}

          override def onPageSelected(i: Int): Unit = {
            Log.d(LOG_TAG, s"page selected ${i}")
            runOnUiThread(new Runnable {
              override def run(): Unit = {
                toolbarContainer.map { toolbarContainer =>
                  toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start()
                  vpPadding(2 * 48 + 8)
                }
              }
            })
          }

        }))
      }
    }) **/
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = super.onCreateOptionsMenu(menu)

  override def onOptionsItemSelected(item: MenuItem): Boolean = super.onOptionsItemSelected(item)

  override def onScrollUp(): Unit = {
    /**
    toolbarContainer.map { toolbarContainer =>
      toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start()
      runUi(vpPadding(2 * 48 + 8))
    } **/
  }

  override def onScrollDown(): Unit = {
    /**
    toolbarContainer.map { toolbarContainer =>
      toolBar.map { toolbar =>
        toolbarContainer.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2)).start()
        runUi(vpPadding(48 + 8))
      }
    } **/
  }

}

trait ScrollDirectionListener {
  def onScrollUp(): Unit
  def onScrollDown(): Unit
}
