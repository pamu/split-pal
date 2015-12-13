package com.rxbytes.splitpal.ui.main.fragments.events

import android.support.v7.widget.RecyclerView
import android.view.{View, ViewGroup}
import android.view.View.OnClickListener
import macroid.{Ui, ActivityContextWrapper}
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.TextTweaks._

/**
  * Created by pnagarjuna on 13/12/15.
  */
class EventsListRecyclerAdapter(events: Seq[Event])(clickListener: Event => Unit)
                               (implicit activityContextWrapper: ActivityContextWrapper)
  extends RecyclerView.Adapter[EventsViewHolder] {

  override def getItemCount: Int = events.length

  override def onBindViewHolder(vh: EventsViewHolder, i: Int): Unit = {
    vh.content.setOnClickListener(new OnClickListener() {
      override def onClick(view: View): Unit = clickListener(events(i))
    })

    runUi(vh.bind(events(i)))

  }


  override def onCreateViewHolder(viewGroup: ViewGroup, i: Int): EventsViewHolder =
    new EventsViewHolder(new EventsLayoutAdapter())

}

class EventsViewHolder(adapter: EventsLayoutAdapter)
  extends RecyclerView.ViewHolder(adapter.content) {

  val content = adapter.content
  val eventName = adapter.eventName

  def bind(event: Event): Ui[_] =
    (eventName <~ tvText(event.name))

}
