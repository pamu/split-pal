package com.rxbytes.splitpal.providers.contacts

import android.content.{ContentProvider, ContentValues}
import android.database.Cursor
import android.net.Uri

/**
  * Created by pnagarjuna on 07/12/15.
  */
class SplitPalContactsProvider extends ContentProvider {

  override def getType(uri: Uri): String = ???

  override def update(uri: Uri, contentValues: ContentValues, s: String, strings: Array[String]): Int = ???

  override def insert(uri: Uri, contentValues: ContentValues): Uri = ???

  override def delete(uri: Uri, s: String, strings: Array[String]): Int = ???

  override def onCreate(): Boolean = ???

  override def query(uri: Uri, strings: Array[String], s: String, strings1: Array[String], s1: String): Cursor = ???

}
