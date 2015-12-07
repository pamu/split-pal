package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.support.v7.widget.{CardView, RecyclerView}
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.Log
import android.view.View.OnClickListener
import android.view.{Gravity, View, ViewGroup}
import android.widget.ImageView.ScaleType
import android.widget.{TextView, ImageView, LinearLayout}
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.rxbytes.splitpal.ui.commons.AsyncImageTweaks._
import com.rxbytes.splitpal.R
import macroid.{ActivityContextWrapper, Tweak, ContextWrapper}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 07/12/15.
  */
class ContactsListRecyclerAdapter(contacts: Seq[Contact])(clickListener: Contact => Unit)
                                 (implicit activityContextWrapper: ActivityContextWrapper)
  extends RecyclerView.Adapter[ContactViewHolder] {

  val LOG_TAG = classOf[ContactsListRecyclerAdapter].getSimpleName

  override def getItemCount: Int = contacts.length

  override def onBindViewHolder(vh: ContactViewHolder, i: Int): Unit = {
    vh.content.setOnClickListener(new OnClickListener {
      override def onClick(view: View): Unit = clickListener(contacts(i))
    })

    Log.d(LOG_TAG, "binding")

    vh.bind(contacts(i))
  }

  override def onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContactViewHolder =
    ContactViewHolder(ContactLayoutAdapter())

}

case class ContactViewHolder(adapter: ContactLayoutAdapter)
                            (implicit activityContextWrapper: ActivityContextWrapper)
  extends ViewHolder(adapter.content) {

  val content = adapter.content
  val profilePic = adapter.profilePic
  val profileName = adapter.profileName
  val profileStatus = adapter.profileStatus
  val inflow = adapter.inflow
  val outflow = adapter.outflow

  def bind(contact: Contact): Unit = {
    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    runUi((profilePic <~ roundedImage(R.drawable.ic_launcher, R.drawable.ic_launcher, avatarSize)) ~
      (profileName <~ tvText(contact.name)) ~
      (profileStatus <~ tvText(contact.status)) ~
      (inflow <~ tvText(s"${contact.inflow}")) ~
      (outflow <~ tvText(s"${contact.outflow}"))
    )
  }

}

case class ContactLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends ContactLayoutStyles {

  var profilePic = slot[ImageView]
  var profileName = slot[TextView]
  var profileStatus = slot[TextView]
  var inflow = slot[TextView]
  var outflow = slot[TextView]

  private def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(profilePic) <~ profilePicStyle,
          l[LinearLayout](
          l[LinearLayout](
            w[TextView] <~ wire(profileName) <~ profileNameStyle,
            w[TextView] <~ wire(profileStatus) <~ profileStatusStyle
          ) <~ profileTopContentStyle,
          l[LinearLayout](
            w[TextView] <~ wire(inflow) <~ inflowStyle,
            w[TextView] <~ wire(outflow) <~ outflowStyle
          ) <~ flowsStyle
        ) <~ profileContentStyle
      ) <~ contactContentStyle
    ) <~ cardStyle
  )

  val content = layout

}

trait ContactLayoutStyles {

  def cardStyle(implicit contextWrapper: ContextWrapper): Tweak[CardView] =
    vMatchWidth

  def profilePicStyle(implicit contextWrapper: ContextWrapper): Tweak[ImageView] = {
    val size = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    lp[LinearLayout](size, size) +
      ivScaleType(ScaleType.CENTER_CROP) +
      llLayoutGravity(Gravity.CENTER_VERTICAL) +
      vMargin(8 dp, 8 dp, 8 dp, 8 dp)
  }

  def profileNameStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvBold +
      vPadding(resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default)) +
      tvText("Name")

  def profileStatusStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      vPadding(resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default)) +
      tvMaxLines(1) +
      tvText("Status")

  def profileTopContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical

  def profileContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llWrapWeightHorizontal +
      llVertical

  def inflowStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightHorizontal +
      vMatchParent +
      tvText("Inflow")

  def outflowStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightHorizontal +
      vMatchParent +
      tvText("Outflow")

  def flowsStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llHorizontal +
      vPadding(resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default),
        resGetDimensionPixelSize(R.dimen.padding_default))

  def contactContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal

}

class PhoneNumber(number: Long)

object PhoneNumber {

  //TODO parse string and check if it can be converted to PhoneNumber, return PhoneNumber in case possible
  def apply(phoneNumberStr: String): PhoneNumber = new PhoneNumber(phoneNumberStr.toLong)

  def unapply(phoneNumber: PhoneNumber): Option[(String)] = Some((phoneNumber.toString))

}

case class Contact(dbId: Long,
                   avatarLink: String,
                   name: String,
                   status: String,
                   inflow: Int,
                   outflow: Int)