package com.rxbytes.splitpal.ui.components

import android.graphics.{Bitmap, Canvas, Paint, PorterDuff, PorterDuffXfermode, Rect}
import com.squareup.picasso.Transformation

class CircularTransformation(size: Int) extends Transformation {

  val radius = Math.ceil(size / 2).toInt

  def transform(source: Bitmap): Bitmap = {
    val output: Bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas: Canvas = new Canvas(output)
    val color: Int = 0xff424242
    val paint: Paint = new Paint
    val rect: Rect = new Rect(0, 0, source.getWidth, source.getHeight)
    val target: Rect = new Rect(0, 0, size, size)
    paint.setAntiAlias(true)
    canvas.drawARGB(0, 0, 0, 0)
    paint.setColor(color)
    canvas.drawCircle(radius, radius, radius, paint)
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
    canvas.drawBitmap(source, rect, target, paint)
    source.recycle()
    output
  }

  def key: String = {
    s"radius-$size"
  }
}