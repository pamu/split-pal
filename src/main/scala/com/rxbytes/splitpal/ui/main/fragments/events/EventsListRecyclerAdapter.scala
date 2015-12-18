package com.rxbytes.splitpal.ui.main.fragments.events

import android.support.v7.widget.RecyclerView
import android.view.View.OnClickListener
import android.view.{ViewGroup, View}
import android.widget.{TextView, BaseAdapter}
import macroid.{IdGeneration, ActivityContextWrapper}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.TextTweaks._

/**
  * Created by pnagarjuna on 13/12/15.
  */
class EventsListAdapter(events: Seq[Event])(clickListener: Event => Unit)
                       (implicit activityContextWrapper: ActivityContextWrapper)
  extends BaseAdapter {

  val LOG_TAG = classOf[EventsListAdapter].getSimpleName

  override def getItemId(i: Int): Long = events(i).hashCode()

  override def getCount: Int = events.length

  override def getView(i: Int, view: View, viewGroup: ViewGroup): View = {
    var newView = view
    if (newView == null) {
      val vh = new EventsViewHolder(new EventsLayoutAdapter())
      vh.content.setOnClickListener(new OnClickListener {
        override def onClick(view: View): Unit = clickListener(events(i))
      })
      newView = vh.content
    }
    runUi(EventsViewHolder.staticBind(newView, events(i)))
    newView
  }

  override def getItem(i: Int): AnyRef = events(i)

}

object EventsListAdapter {

  def apply(events: Seq[Event])
           (implicit activityContextWrapper: ActivityContextWrapper): EventsListAdapter =
    new EventsListAdapter(events)(event => Unit)

  def apply(events: Seq[Event], clickListener: Event => Unit)
           (implicit activityContextWrapper: ActivityContextWrapper): EventsListAdapter =
    new EventsListAdapter(events)(clickListener)

}

class EventsViewHolder(adapter: EventsLayoutAdapter)
  extends RecyclerView.ViewHolder(adapter.content) {

  val content = adapter.content
  val eventName = adapter.eventName

  /**
  def bind(event: Event): Ui[_] =
    (eventName <~ tvText(event.name)) **/

}

object EventsViewHolder extends IdGeneration {

  def staticBind(view: View, event: Event) =
    (view.find[TextView](Id.eventName) <~ tvText(event.name))

}
