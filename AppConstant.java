package com.tecksky.justreminder.globle;

public class AppConstant {

    // regular expressions

    public static final String EMAIL_EXPRESS = "^[A-Za-z0-9]+([\\.\\-_]{1}[A-Za-z0-9]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+){0,1}(\\.[A-Za-z]{2,4})$";
    public static final String MOBILE_EXPRESS = "^[+]?[0-9]{10,13}$";
    public static final String NAME_EXPRESS = "^[\\p{L} .'-]+$";
    public static final String IND_ZIP_EXPRESS = "^[0-9]{6}$";

    // app constant

    public static final String success = "success";
    public static final String fail = "fail";

    public static final long PULL_TO_REFRESH = 0;

    public static final String EMPTY_STRING = "";

    public static final String PLEASE_WAIT = "Please wait";
    public static final String NO_INTERNET_CONNECTION = "No internet connection";

    public static final String SOMETHING_WRONG_TRY_AGAIN = "Something may went wrong, Please try again";
    public static final int SPLASH_TIME_OUT = 8000;

    // GCM Notification
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PREF_GCM_DEVICE_ID = "gcm_device_id";
}
