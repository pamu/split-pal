package com.rxbytes.splitpal.ui.components

import android.content.Context
import android.graphics.Paint.Style
import android.graphics.{Canvas, Paint}
import android.util.AttributeSet
import android.view.View
import macroid.Tweak

class CircleView(context: Context, attr: AttributeSet, defStyleAttr: Int)
    extends View(context, attr, defStyleAttr) {

  def this(context: Context) = this(context, null, 0)

  def this(context: Context, attr: AttributeSet) = this(context, attr, 0)

  private val paint = {
    val paint : Paint = new Paint()
    paint.setStyle(Style.FILL)
    paint
  }

  def setColor(color: Int) = {
    paint.setColor(color)
  }

  override def onDraw(canvas: Canvas): Unit = {
    super.onDraw(canvas)
    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint)
  }

}

object CircleView {
  type W = CircleView

  def cvColor(color: Int) = Tweak[W] (_.setColor(color))
}
