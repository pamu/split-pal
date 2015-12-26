package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.{View, ViewGroup}
import android.widget._
import com.rxbytes.splitpal.ui.commons.AsyncImageTweaks._
import com.rxbytes.splitpal.R
import com.rxbytes.splitpal.ui.main.fragments.contacts.styles.ContactLayoutStyles
import macroid.{Tweak, IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import scala.language.postfixOps

/**
  * Created by pnagarjuna on 07/12/15.
  */
class ContactsListAdapter(initialItems: Seq[Contact])(clickListener: Contact => Unit)
                         (implicit activityContextWrapper: ActivityContextWrapper)
  extends BaseAdapter {

  val LOG_TAG = classOf[ContactsListAdapter].getSimpleName

  //immutable data structure assigned to mutable variable
  var contacts = initialItems

  def addItems(moreItems: Seq[Contact]): Unit = {
    contacts = contacts ++ moreItems
    notifyDataSetChanged()
  }

  override def getItemId(i: Int): Long = contacts(i).hashCode()

  override def getCount: Int = contacts.length

  override def getView(i: Int, view: View, viewGroup: ViewGroup): View = {
    var newView = view
    if (newView == null) {
      val vh = new ContactViewHolder(new ContactLayoutAdapter())
      vh.content.setOnClickListener(new View.OnClickListener() {
        override def onClick(view: View): Unit = clickListener(contacts(i))
      })
      newView = vh.content
    }
    runUi(ContactViewHolder.staticBind(newView, contacts(i)))
    newView
  }

  override def getItem(i: Int): AnyRef = contacts(i)

}

object ContactsListAdapter {

  def apply(contacts: Seq[Contact])
           (implicit activityContextWrapper: ActivityContextWrapper): ContactsListAdapter =
    new ContactsListAdapter(contacts)(contact => Unit)

  def apply(contacts: Seq[Contact], clickListener: Contact => Unit)
           (implicit activityContextWrapper: ActivityContextWrapper): ContactsListAdapter =
    new ContactsListAdapter(contacts)(clickListener)

}

class ContactViewHolder(adapter: ContactLayoutAdapter)
                       (implicit activityContextWrapper: ActivityContextWrapper)
  extends ViewHolder(adapter.content) {

  val content = adapter.content
  val profilePic = adapter.profilePic
  val profileName = adapter.profileName
  val profileStatus = adapter.profileStatus

  /**
  def bind(contact: Contact) = {
    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    (profilePic <~ roundedImage("http://fb.com", R.drawable.user_placeholder, avatarSize)) ~
      (profileName <~ tvText(contact.name)) ~
      (profileStatus <~ (ContactViewHolder.getStatus(contact.flow)))
  } **/

}

object ContactViewHolder extends IdGeneration {

  def getStatus(flow: Int): Tweak[TextView] =
    if (flow > 0) tvText(s"owes you ${flow}") else tvText(s"you owe ${flow}")

  def staticBind(view: View, contact: Contact)(implicit activityContextWrapper: ActivityContextWrapper) = {
    val avatarSize = resGetDimensionPixelSize(R.dimen.main_list_avatar_size)
    (view.find[ImageView](Id.profilePic) <~ roundedImage("http://fb.com", R.drawable.user_placeholder, avatarSize)) ~
      (view.find[TextView](Id.profileName) <~ tvText(contact.name)) ~
      (view.find[TextView](Id.profileStatus) <~ getStatus(contact.flow))
  }

}

class ContactLayoutAdapter(implicit activityContextWrapper: ActivityContextWrapper)
  extends ContactLayoutStyles with IdGeneration {

  var profilePic = slot[ImageView]
  var profileName = slot[TextView]
  var profileStatus = slot[TextView]

  private def layout = getUi(
    l[CardView](
      l[LinearLayout](
        w[ImageView] <~ wire(profilePic) <~ profilePicStyle <~ id(Id.profilePic),
        l[LinearLayout](
          w[TextView] <~ wire(profileName) <~ profileNameStyle <~ id(Id.profileName),
          //w[ImageView] <~ lineStyle,
          w[TextView] <~ wire(profileStatus) <~ profileStatusStyle <~ id(Id.profileStatus)
        ) <~ profileContentStyle
      ) <~ contactContentStyle
    ) <~ cardStyle
  )

  val content = layout

}


case class Contact(id: Long,
                   avatarLink: Option[String],
                   name: String,
                   flow: Int)