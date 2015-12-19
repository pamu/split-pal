package com.rxbytes.splitpal.ui.commons

import android.view.View
import android.widget.{RelativeLayout, ImageView}
import com.fortysevendeg.macroid.extras.DeviceVersion._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import com.rxbytes.splitpal.ui.components.CircularTransformation
import com.squareup.picasso.Picasso
import macroid.{ContextWrapper, ActivityContextWrapper, Tweak}
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

  def srcImage(url: String)(implicit context: ActivityContextWrapper): Tweak[W] = Tweak[W](
    imageView => {
      Picasso.`with`(context.getOriginal)
          .load(url)
          .into(imageView)
    }
  )

}

object RelativeLayoutTweaks {

  type W = RelativeLayout

  def rlAlignParentLeft(implicit contextWrapper: ContextWrapper): Tweak[View] = Tweak[View] {
    view =>
      val param: RelativeLayout.LayoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams)
      param.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
      view.setLayoutParams(param)
  }

  def rlAlignParentRight(implicit contextWrapper: ContextWrapper): Tweak[View] = Tweak[View] {
    view =>
      val param: RelativeLayout.LayoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams)
      param.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
      param.addRule(RelativeLayout.CENTER_VERTICAL)
      view.setLayoutParams(param)
  }

  def rlAlignParent(verb: Int)(implicit contextWrapper: ContextWrapper): Tweak[View] = Tweak[View] {
    view =>
      val param: RelativeLayout.LayoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams)
      param.addRule(verb)
      view.setLayoutParams(param)
  }

  def rlGravity(gravity: Int): Tweak[W] = Tweak[W](_.setGravity(gravity))

  def rlLayoutMargin(
                      marginLeft: Int = 0,
                      marginTop: Int = 0,
                      marginRight: Int = 0,
                      marginBottom: Int = 0): Tweak[View] = Tweak[View] {
    view ⇒
      val params = new RelativeLayout.LayoutParams(view.getLayoutParams)
      params.setMargins(marginLeft, marginTop, marginRight, marginBottom)
      view.setLayoutParams(params)
  }

}

