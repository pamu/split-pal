package com.rxbytes.splitpal.utils.commons

import android.database.Cursor

import scala.annotation.tailrec
import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Failure, Success, Try}

/**
  * Created by pnagarjuna on 22/12/15.
  */
object CommonUtils {

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
      case Some(cursorObject) if cursorObject.moveToFirst() =>
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
