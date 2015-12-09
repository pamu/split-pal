package com.rxbytes.splitpal.utils

import android.database.Cursor

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

/**
  * Created by pnagarjuna on 09/12/15.
  */
object Contacts {

  val LOG_TAG = classOf[Contacts.type].getSimpleName

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
