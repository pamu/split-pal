package com.rxbytes.splitpal.ui.commons

import android.widget.ImageView
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.ui.components.CircularTransformation
import com.squareup.picasso.Picasso
import macroid.{ActivityContextWrapper, Tweak}

import scala.language.postfixOps

object AsyncImageTweaks {
  type W = ImageView

  def roundedImage(url: String,
        placeHolder: Int,
        size: Int)(implicit context: ActivityContextWrapper) = CurrentVersion match {
    case sdk if sdk >= Lollipop =>
      srcImage(url, placeHolder) + vCircleOutlineProvider(0)
    case _ =>
      roundedImageTweak(url, placeHolder, size)
  }

  def roundedImage(url: Int,
        placeHolder: Int,
        size: Int)(implicit context: ActivityContextWrapper) = CurrentVersion match {
    case sdk if sdk >= Lollipop =>
      srcImage(url, placeHolder) + vCircleOutlineProvider(0)
    case _ =>
      roundedImageTweak(url, placeHolder, size)
  }

  private def roundedImageTweak(
      url: String,
      placeHolder: Int,
      size: Int
      )(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .transform(new CircularTransformation(size))
          .placeholder(placeHolder)
          .into(imageView)
    }
  )

  private def roundedImageTweak(
      url: Int,
      placeHolder: Int,
      size: Int
      )(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .transform(new CircularTransformation(size))
          .placeholder(placeHolder)
          .into(imageView)
    }
  )


  def srcImage(
      url: String,
      placeHolder: Int
      )(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .placeholder(placeHolder)
          .into(imageView)
    }
  )

  def srcImage(
      url: Int,
      placeHolder: Int
      )(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .placeholder(placeHolder)
          .into(imageView)
    }
  )

  def srcImage(url: String)(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .into(imageView)
    }
  )
  def srcImage(url: Int)(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .into(imageView)
    }
  )
}

