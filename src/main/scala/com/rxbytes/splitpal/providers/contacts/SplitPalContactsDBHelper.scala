package com.rxbytes.splitpal.providers.contacts

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}

/**
  * Created by pnagarjuna on 07/12/15.
  */
class SplitPalContactsDBHelper(context: Context)
  extends SQLiteOpenHelper(
    context,
    SplitPalContactsDBHelper.databaseName,
    null,
    SplitPalContactsDBHelper.databaseVersion) {

  override def onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = {
    if (newVersion > oldVersion) {

    }
  }

  override def onCreate(sqLiteDatabase: SQLiteDatabase): Unit = {
    sqLiteDatabase.execSQL("")
  }

}

object SplitPalContactsDBHelper {
  val id = "_id"
  val databaseName = "contactsDatabase"
  val databaseVersion = 1
}