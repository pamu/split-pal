package com.rxbytes.splitpal.ui.main.fragments.commons

import android.support.v7.widget.{LinearLayoutManager, GridLayoutManager, RecyclerView}
import android.widget._
import com.fortysevendeg.macroid.extras.DeviceMediaQueries._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import com.rxbytes.splitpal.ui.commons.ListItemDecorator
import macroid.{Tweak, Ui, ActivityContextWrapper}
import macroid.FullDsl._
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 14/12/15.
  */
trait ListViewLayout
  extends ListViewStyles {

  self: ContextWrapperProvider =>

  var progressBar = slot[ProgressBar]
  var recyclerHolder = slot[LinearLayout]
  var recyclerList = slot[RecyclerView]
  var fastScroller = slot[VerticalRecyclerViewFastScroller]
  var placeholder = slot[LinearLayout]
  var msg = slot[TextView]
  var reloadBtn = slot[Button]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      l[LinearLayout](
        w[RecyclerView] <~ wire(recyclerList) <~ recyclerListStyle,
        w[VerticalRecyclerViewFastScroller] <~ wire(fastScroller) <~ fastScrollStyle
      ) <~ wire(recyclerHolder) <~ recyclerHolderStyle,
      l[LinearLayout](
        w[TextView] <~ wire(msg) <~ msgStyle,
        w[Button] <~ wire(reloadBtn) <~ btnStyle
      ) <~ wire(placeholder) <~ placeholderContentStyle
    ) <~ contentStyle
  )

  def layoutManager(implicit activityContextWrapper: ActivityContextWrapper) =
    landscapeTablet ?
      new GridLayoutManager(activityContextWrapper.application, 3) |
      tablet ?
        new GridLayoutManager(activityContextWrapper.application, 2) | new LinearLayoutManager(activityContextWrapper.application)

  def init()(implicit activityContextWrapper: ActivityContextWrapper): Ui[_] =
    (recyclerHolder <~ vVisible) ~
      (recyclerList <~
        rvLayoutManager(layoutManager) <~
        rvAddItemDecoration(new ListItemDecorator)) ~
      (fastScroller <~ Tweak[VerticalRecyclerViewFastScroller](_.setRecyclerView(recyclerList.get))) ~
      (placeholder <~ vGone) ~
      (progressBar <~ vGone)

  def loading(): Ui[_] =
    (progressBar <~ vVisible) ~
      (recyclerHolder <~ vGone) ~
      (placeholder <~ vGone)

  def failed(): Ui[_] =
    (progressBar <~ vGone) ~
      (recyclerHolder <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.error)) ~
      (reloadBtn <~ vVisible)

  def empty(): Ui[_] =
    (progressBar <~ vGone) ~
      (recyclerHolder <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.empty)) ~
      (reloadBtn <~ vGone)

  def adapter[VH <: RecyclerView.ViewHolder](adapter: RecyclerView.Adapter[VH])(implicit activityContextWrapper: ActivityContextWrapper): Ui[_] =
    (progressBar <~ vGone) ~
      (placeholder <~ vGone) ~
      (recyclerHolder <~ vVisible) ~
      (recyclerList <~ rvLayoutManager(layoutManager)
        <~ rvAddItemDecoration(new ListItemDecorator)
        <~ rvAdapter(adapter))

  def reloadList[VH <: RecyclerView.ViewHolder](adapterInstance: RecyclerView.Adapter[VH])
                                               (implicit activityContextWrapper: ActivityContextWrapper): Ui[_] = adapterInstance.getItemCount match {
    case 0 => empty()
    case _ => adapter(adapterInstance)
  }

}
