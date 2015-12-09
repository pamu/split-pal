package contacts;

import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by pnagarjuna on 09/12/15.
 */
public class PhoneContactColumns {

    //Display Name and Has phone number
    public static Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    public static String _ID = ContactsContract.Contacts._ID;
    public static String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    public static String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

    //Phone Number
    public static Uri PHONE_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    public static String PHONE_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    public static String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    //Email and Email Data
    public static Uri EMAIL_CONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    public static String EMAIL_CONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    public static String DATA = ContactsContract.CommonDataKinds.Email.DATA;

}
