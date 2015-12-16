package com.rxbytes.splitpal.ui.main.fragments.commons

import android.widget._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.ui.main.fragments.commons.ListViewTweaks._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.commons.ContextWrapperProvider
import macroid.{Ui, ActivityContextWrapper}
import macroid.FullDsl._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 14/12/15.
  */
trait ListViewLayout
  extends ListViewStyles {

  self: ContextWrapperProvider =>

  var progressBar = slot[ProgressBar]
  var listHolder = slot[LinearLayout]
  var list = slot[ListView]
  var placeholder = slot[LinearLayout]
  var msg = slot[TextView]
  var reloadBtn = slot[Button]

  def layout(implicit activityContextWrapper: ActivityContextWrapper) = getUi(
    l[FrameLayout](
      w[ProgressBar] <~ wire(progressBar) <~ progressBarStyle,
      l[LinearLayout](
        w[ListView] <~ wire(list) <~ listStyle
      ) <~ wire(listHolder) <~ listHolderStyle,
      l[LinearLayout](
        w[TextView] <~ wire(msg) <~ msgStyle,
        w[Button] <~ wire(reloadBtn) <~ btnStyle
      ) <~ wire(placeholder) <~ placeholderContentStyle
    ) <~ contentStyle
  )

  /**
  def layoutManager(implicit activityContextWrapper: ActivityContextWrapper) =
    landscapeTablet ?
      new GridLayoutManager(activityContextWrapper.application, 3) |
      tablet ?
        new GridLayoutManager(activityContextWrapper.application, 2) |
      new LinearLayoutManager(activityContextWrapper.application) **/

  def init: Ui[_] =
    (listHolder <~ vVisible) ~
      (list <~ lvEnableFastScroll) ~
      (placeholder <~ vGone) ~
      (progressBar <~ vGone)

  def loading: Ui[_] =
    (progressBar <~ vVisible) ~
      (listHolder <~ vGone) ~
      (placeholder <~ vGone)

  def failed: Ui[_] =
    (progressBar <~ vGone) ~
      (listHolder <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.error)) ~
      (reloadBtn <~ vVisible)

  def empty: Ui[_] =
    (progressBar <~ vGone) ~
      (listHolder <~ vGone) ~
      (placeholder <~ vVisible) ~
      (msg <~ vVisible <~ tvText(R.string.empty)) ~
      (reloadBtn <~ vGone)

  def adapter[BA <: BaseAdapter](baseAdapter: BaseAdapter): Ui[_] =
    (progressBar <~ vGone) ~
      (placeholder <~ vGone) ~
      (listHolder <~ vVisible) ~
      (list <~ lvAdapter(baseAdapter))

  def reloadList[BA <: BaseAdapter](baseAdapter: BaseAdapter): Ui[_] = baseAdapter.getCount match {
    case 0 => empty
    case _ => adapter(baseAdapter)
  }

}
