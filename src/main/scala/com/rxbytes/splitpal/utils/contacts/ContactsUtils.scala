package com.rxbytes.splitpal.utils.contacts

/**
  * Created by pnagarjuna on 22/12/15.
  */
object ContactsUtils {

  implicit class PhoneNumberUtils(val str: String) extends AnyVal {

    def tenDigitNumber: Option[Long] = {
      val trimmedStr = str.trim
      val trimmedStrLen = trimmedStr.length
      if (trimmedStrLen >= 10) {
        val extra = trimmedStrLen - 10
        val str10 = trimmedStr.substring(extra)
        if (str10.matches("""[\d+]*""")) Some(str10.toLong) else None
      } else None
    }

    def countryCode: CountryCode.CountryCode = CountryCode.IN

  }

}

object CountryCode extends Enumeration {
  type CountryCode = Value
  val IN = Value("India")
  val US = Value("America")
  val UK = Value("England")
}
