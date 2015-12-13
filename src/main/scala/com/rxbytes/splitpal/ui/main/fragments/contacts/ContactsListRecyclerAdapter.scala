package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.support.v7.widget.{CardView, RecyclerView}
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View.OnClickListener
import android.view.{View, ViewGroup}
import android.widget.{TextView, ImageView, LinearLayout}
import com.rxbytes.splitpal.ui.commons.AsyncImageTweaks._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactLayoutStyles
import macroid.{Ui, ActivityContextWrapper}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
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
    runUi(vh.bind(contacts(i)))
  }

  override def onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContactViewHolder =
    new ContactViewHolder(new ContactLayoutAdapter())

}

class ContactViewHolder(adapter: ContactLayoutAdapter)
                       (implicit activityContextWrapper: ActivityContextWrapper)
  extends ViewHolder(adapter.content) {

  val content = adapter.content
  val profilePic = adapter.profilePic
  val profileName = adapter.profileName
  val profileStatus = adapter.profileStatus

  def bind(contact: Contact): Ui[_] = {
    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    (profilePic <~ roundedImage("http://fb.com", R.drawable.ic_launcher, avatarSize)) ~
      (profileName <~ tvText(contact.name)) ~
      (profileStatus <~ (if (contact.flow > 0) tvText(s"owes you ${contact.flow}") else tvText(s"you owe ${contact.flow}")))
  }

}

class ContactLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
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
          //w[ImageView] <~ lineStyle,
          w[TextView] <~ wire(profileStatus) <~ profileStatusStyle
        ) <~ profileContentStyle
      ) <~ contactContentStyle
    ) <~ cardStyle
  )

  val content = layout

}



case class Contact(dbId: Long,
                   avatarLink: String,
                   name: String,
                   flow: Int)