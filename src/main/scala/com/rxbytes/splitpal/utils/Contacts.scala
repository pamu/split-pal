package com.rxbytes.splitpal.utils

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.rxbytes.splitpal.ui.main.fragments.contacts.Contact
import macroid.ContextWrapper

/**
  * Created by pnagarjuna on 09/12/15.
  */
object Contactsq {

  def retrievePhoneContacts(implicit contextWrapper: ContextWrapper): Seq[Contact] = {
    val uri = Phone.CONTENT_URI
    val projection = Array[String](
      Phone.NUMBER
    )

    Seq.empty[Contact]
  }

}
