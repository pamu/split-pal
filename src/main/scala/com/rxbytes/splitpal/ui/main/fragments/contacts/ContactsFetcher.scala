package com.rxbytes.splitpal.ui.main.fragments.contacts

import android.database.Cursor
import com.rxbytes.splitpal.utils.commons.CommonUtils._
import contacts.PhoneContactColumns
import macroid.ContextWrapper

import scala.concurrent.Future
import scala.util.Try

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by pnagarjuna on 09/12/15.
  */

case class CompleteContact(contactId: Int,
                           displayName: String,
                           phoneNumber: String,
                           email: String,
                           emailData: String)

object ContactsFetcher {

  case class ContactWithDisplayName(contactId: Int,
                                    displayName: String,
                                    hasPhoneNumber: Boolean)

  case class ContactWithPhoneNumber(contactId: Int,
                                    phoneNumber: String)

  case class ContactWithNameAndPhone(contactId: Int,
                                     displayName: String,
                                     phoneNumber: String)

  def queryContactsWithDisplayName(limit: Int, offset: Int)(implicit contextWrapper: ContextWrapper): Seq[ContactWithDisplayName] = {

    val cursor = Option[Cursor](
      contextWrapper.application.getContentResolver
        .query(
          PhoneContactColumns.CONTENT_URI,
          Array(
            PhoneContactColumns._ID,
            PhoneContactColumns.DISPLAY_NAME,
            PhoneContactColumns.HAS_PHONE_NUMBER
          ),
          s"${PhoneContactColumns.HAS_PHONE_NUMBER} != ?",
          Array("0"),
          s" ${PhoneContactColumns._ID} ASC limit $limit offset $offset"
        )
    )

    def conversionFunction: Cursor => ContactWithDisplayName = cursor => {
      val id = cursor.getInt(cursor.getColumnIndex(PhoneContactColumns._ID))
      val displayName = cursor.getString(cursor.getColumnIndex(PhoneContactColumns.DISPLAY_NAME))
      val hasPhoneNumber = cursor.getString(cursor.getColumnIndex(PhoneContactColumns.HAS_PHONE_NUMBER))
      ContactWithDisplayName(id, displayName, hasPhoneNumber.toInt > 0)
    }

    getListFromCursor(cursor, conversionFunction)

  }

  def queryContactsForPhoneNumber(contactsWithDisplayName: Seq[ContactWithDisplayName])
                                 (implicit contextWrapper: ContextWrapper): Seq[ContactWithNameAndPhone] = {

    contactsWithDisplayName.flatMap { contactWithDisplayName =>
      val cursor = Option[Cursor](contextWrapper.application.getContentResolver.query(
        PhoneContactColumns.PHONE_CONTENT_URI,
        Array(
          PhoneContactColumns.PHONE_CONTACT_ID,
          PhoneContactColumns.NUMBER
        ),
        s"${PhoneContactColumns.PHONE_CONTACT_ID} = ? and ${PhoneContactColumns.NUMBER} not null",
        Array(contactWithDisplayName.contactId.toString),
        null,
        null
      ))

      def conversionFunction: Cursor => ContactWithPhoneNumber = cursor => {
        val id = cursor.getInt(cursor.getColumnIndex(PhoneContactColumns.PHONE_CONTACT_ID))
        val number = cursor.getString(cursor.getColumnIndex(PhoneContactColumns.NUMBER))
        ContactWithPhoneNumber(
          id,
          number.split("\\s+").map(_.trim).reduce(_ + _)
        )
      }

      getListFromCursor(cursor, conversionFunction).map { contact =>
        ContactWithNameAndPhone(contact.contactId, contactWithDisplayName.displayName, contact.phoneNumber)
      }
    }

  }

  def contacts(implicit contextWrapper: ContextWrapper): Seq[Contact] = {
    queryContactsForPhoneNumber(queryContactsWithDisplayName(10, 0))
      .map { contact => contact.phoneNumber -> contact }(scala.collection.breakOut).toMap
      .map { pair => pair._2 }.toSeq
      .map { contactWithNameAndPhone =>
        Contact(1, None, contactWithNameAndPhone.phoneNumber, 100)
      }
  }

  def contactsAsync(implicit contextWrapper: ContextWrapper): Future[Seq[Contact]] =
    tryToFuture(Try(contacts))


}
