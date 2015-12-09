package com.rxbytes.splitpal.utils

import android.database.Cursor
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import com.rxbytes.splitpal.ui.main.fragments.contacts.Contact
import macroid.ContextWrapper

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

/**
  * Created by pnagarjuna on 09/12/15.
  */
object Contacts {

  val LOG_TAG = classOf[Contacts.type].getSimpleName

  /*
  def retrievePhoneContacts(implicit contextWrapper: ContextWrapper): Seq[Contact] = {
    val cursor = contextWrapper.application.getContentResolver.query(Phone.CONTENT_URI, null, null, null, null)
    cursorToContactsList(cursor) {
      cursor =>
        var contacts = Seq.empty[Contact]
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
          val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
          val contact = Contact(1, "", "Name", number, 100)
          contacts = contacts ++ Seq(contact)
          cursor.moveToNext()
        }
        contacts
    }
  }

  private[this] def cursorToContactsList(cursor: Cursor)(f: Cursor => Seq[Contact]): Seq[Contact] = {
    try {
      f(cursor)
    } catch {
      case ex: Exception =>
        Log.d(LOG_TAG, ex.getMessage)
        Seq.empty[Contact]
    } finally {
      cursor.close()
    }
  } **/

  def getListFromCursor[T](cursor: Option[Cursor], conversionFunction: Cursor => T): Seq[T] = {
    @tailrec
    def getListFromEntityLoop(cursor: Cursor, result: Seq[T]): Seq[T] = {
      cursor match {
        case validCursor if validCursor.isAfterLast => result
        case _ => {
          val entity = conversionFunction(cursor)
          cursor.moveToNext
          getListFromEntityLoop(cursor, entity +: result)
        }
      }
    }

    cursor match {
      case Some(cursorObject) =>
       val entityList = getListFromEntityLoop(cursorObject, Seq.empty[T])
        cursorObject.close()
        entityList
      case _ => Seq.empty[T]
    }

  }

  def tryToFuture[T](f: => Try[T])(implicit executionContext: ExecutionContext): Future[T] =
    Future(f).flatMap {
      case Success(success) => Future.successful(success)
      case Failure(failure) => Future.failed(failure)
    }


}
