package com.rxbytes.splitpal.ui.commons

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.State
import android.view.View
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.rxbytes.splitpal.R
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 07/12/15.
  */
class ListItemDecorator(implicit contextWrapper: ContextWrapper)
  extends RecyclerView.ItemDecoration {

  override def getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
    outRect.left = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.right = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.bottom = resGetDimensionPixelSize(R.dimen.padding_default)
    outRect.top = resGetDimensionPixelSize(R.dimen.padding_default)
  }
}
