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

    public static final String PREF_VIBRATE = "vibrate";
    public static final String PREF_RINGTONE = "ringtone";
    public static final String PREF_THEME_COLOR = "theme_color";
    public static final String PREF_NOTIFICATION_STATUS = "notification_status";
    public static final String PREF_CUSTOM_NOTIFICATION_STATUS = "custom_notification_status";
    public static final String PREF_DATE_FORMAT = "date_format";
    public static final String PREF_TIME_FORMAT = "time_format";
    public static final String PREF_START_DAY = "start_day";
    public static final String PREF_NEW_TASK_STATUS = "new_task_status";
    public static final String PREF_IS_APP_DEFAULT = "is_app_default";

    public static final String COLOR_RED = "color_red";
    public static final String COLOR_BLUE = "color_blue";
    public static final String COLOR_BLACK = "color_balck";
    public static final String COLOR_GREEN = "color_green";
    public static final String COLOR_PINK = "color_pink";
    public static final String COLOR_GRAY = "color_gray";

    public static final String FRAGMENT_HOME = "home_fragment";
    public static final String FRAGMENT_HISTORY = "history_fragment";
    public static final String FRAGMENT_SETTING = "setting_fragment";
    public static final String FRAGMENT_BACKUP_RESTORE = "backup_restore_fragment";
    public static final String FRAGMENT_FESTIVALS = "festivals_fragment";

}