package com.rxbytes.splitpal.providers.contacts

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}
import android.util.Log

/**
  * Created by pnagarjuna on 07/12/15.
  */
class SplitPalContactsDBHelper(context: Context)
  extends SQLiteOpenHelper(
    context,
    SplitPalContactsDBHelper.databaseName,
    null,
    SplitPalContactsDBHelper.databaseVersion) {

  val LOG_TAG = classOf[SplitPalContactsDBHelper].getSimpleName

  val ddl =
    s"""
       |create table ${ContactsContract.contactsTable} (
       |  ${SplitPalContactsDBHelper.id} INTEGER PRIMARY KEY AUTOINCREMENT,
       |  ${ContactsContract.name} TEXT NOT NULL,
       |  ${ContactsContract.phone} TEXT NOT NULL,
       |  ${ContactsContract.registered} INT DEFAULT 0 NOT NULL,
       |  ${ContactsContract.countryCode} TEXT DEFAULT 'IN' NOT NULL
       |);
     """.stripMargin

  override def onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = {
    Log.d(LOG_TAG, s"SQLite new version ${newVersion} and old version ${oldVersion}")
    if (newVersion > oldVersion) {
      Log.d(LOG_TAG, "new version is greater than old version")
      sqLiteDatabase.execSQL(s"DROP IF EXISTS TABLE ${ContactsContract.contactsTable}")
      onCreate(sqLiteDatabase)
    }
  }

  override def onCreate(sqLiteDatabase: SQLiteDatabase): Unit = {
    Log.d(LOG_TAG, "onCreate method")
    sqLiteDatabase.execSQL(ddl)
  }

}

object ContactsContract {
  val contactsTable = "contacts"
  val name = "name"
  val phone = "phone"
  val registered = "registered"
  val countryCode = "country_code"
}

object SplitPalContactsDBHelper {
  val id = "_id"
  val databaseName = "contactsDatabase"
  val databaseVersion = 1
}