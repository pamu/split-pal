package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.support.v7.widget.{CardView, RecyclerView}
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.TextUtils.TruncateAt
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

  def bind(contact: Contact): Unit = {
    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    runUi((profilePic <~ roundedImage(R.drawable.ic_launcher, R.drawable.ic_launcher, avatarSize)) ~
      (profileName <~ tvText(contact.name)) ~
      (profileStatus <~ (if (contact.flow > 0) tvText(s"owes you ${contact.flow}") else tvText(s"you owe ${contact.flow}")))
    )
  }

}

case class ContactLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends ContactLayoutStyles {

  var profilePic = slot[ImageView]
  var profileName = slot[TextView]
  var profileStatus = slot[TextView]

  private def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(profilePic) <~ profilePicStyle,
        l[LinearLayout](
          w[TextView] <~ wire(profileName) <~ profileNameStyle,
          w[TextView] <~ wire(profileStatus) <~ profileStatusStyle
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
      llLayoutGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT) +
      vMargin(8 dp, 8 dp, 24 dp, 8 dp)
  }

  def profileNameStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvSizeResource(R.dimen.title_font) +
      tvColorResource(R.color.colorPrimary) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def profileStatusStyle(implicit contextWrapper: ContextWrapper): Tweak[TextView] =
    llMatchWeightVertical +
      vMatchParent +
      tvNormalLight +
      tvItalicLight +
      tvSizeResource(R.dimen.status_font) +
      tvColorResource(R.color.colorPrimary) +
      llLayoutMargin(0,
        resGetDimensionPixelSize(R.dimen.padding_small),
        resGetDimensionPixelSize(R.dimen.padding_tiny),
        resGetDimensionPixelSize(R.dimen.padding_small)) +
      tvMaxLines(1) +
      tvEllipsize(TruncateAt.END)

  def profileContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    llVertical +
      llWrapWeightHorizontal

  def contactContentStyle(implicit contextWrapper: ContextWrapper): Tweak[LinearLayout] =
    vMatchWidth +
      llGravity(Gravity.CENTER) +
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
                   flow: Int)