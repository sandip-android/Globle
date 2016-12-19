package com.tecksky.justreminder.globle;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tecksky.justreminder.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppMethod {

    public static final String PREFS_NAME = "easygove";
    public static ProgressDialog p_Dialog;
    //static ObjectMapper mapper = null;
    static Lock lock = new ReentrantLock();

    // to check whether internet is connected or not
    @SuppressWarnings("deprecation")
    public static boolean isNetworkConnected(Activity activity) {
        ConnectivityManager connectivity = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    // display toast
    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    // show progress
    public static void showProgressDialog(Activity activity, String msg) {
        p_Dialog = new ProgressDialog(activity);
        p_Dialog.setMessage(msg);
        p_Dialog.setCanceledOnTouchOutside(false);
        p_Dialog.show();
    }

    // dismiss progress
    public static void dismissProgressDialog(Activity activity) {
        if (p_Dialog != null && p_Dialog.isShowing())
            p_Dialog.dismiss();
    }

    // string preference
    public static boolean setStringPreference(Activity activity, String key, String value) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static String getStringPreference(Activity activity, String key) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String value = settings.getString(key, "");
        return value;
    }

    // int preference
    public static boolean setIntegerPreference(Activity activity, String key, int value) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getIntegerPreference(Context activity, String key) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int value = settings.getInt(key, -1);
        return value;
    }

    // boolean preference
    public static boolean setBooleanPreference(Context activity, String key, Boolean value) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static Boolean getBooleanPreference(Context activity, String key) {
        SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Boolean value = settings.getBoolean(key, false);
        return value;
    }

    // check whether string is valid or not
    public static Boolean isStringValid(String text) {
        if (text != null && !text.equalsIgnoreCase("null") && !text.equalsIgnoreCase(""))
            return true;
        else
            return false;
    }

    // return valid string text else defaultText
    public static String getString(String text, String defaultText) {
        if (text != null && !text.equalsIgnoreCase("null") && !text.equalsIgnoreCase(""))
            return text;
        else
            return defaultText;
    }

    /*public static synchronized ObjectMapper getMapper() {

        if (mapper != null) {
            return mapper;
        }
        try {
            lock.lock();
            if (mapper == null) {
                mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
            }
            lock.unlock();
        } catch (Exception e) {
            if (e != null)
                Log.e("Mapper", "Mapper Initialization Failed. Exception :: " + e.getMessage());
        }

        return mapper;
    }*/

    public static void openFile(Activity activity, String filename) {

        File openFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Test Saviour"
                + File.separator + filename);

        Uri uri = Uri.fromFile(openFile);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (openFile.toString().contains(".doc") || openFile.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (openFile.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (openFile.toString().contains(".ppt") || openFile.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (openFile.toString().contains(".xls") || openFile.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (openFile.toString().contains(".zip") || openFile.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/zip");
        } else if (openFile.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (openFile.toString().contains(".wav") || openFile.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (openFile.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (openFile.toString().contains(".jpg") || openFile.toString().contains(".jpeg")
                || openFile.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (openFile.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (openFile.toString().contains(".3gp") || openFile.toString().contains(".mpg")
                || openFile.toString().contains(".mpeg") || openFile.toString().contains(".mpe")
                || openFile.toString().contains(".mp4") || openFile.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            // if you want you can also define the intent type for any other
            // file

            // additionally use else clause below, to manage other unknown
            // extensions
            // in this case, Android will show all applications installed on the
            // device
            // so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void openFileFromURL(Activity activity, String url) {

        Uri uri = Uri.parse(url);
        String openFile = url;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (openFile.toString().contains(".doc") || openFile.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if (openFile.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if (openFile.toString().contains(".ppt") || openFile.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if (openFile.toString().contains(".xls") || openFile.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if (openFile.toString().contains(".zip") || openFile.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/zip");
        } else if (openFile.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if (openFile.toString().contains(".wav") || openFile.toString().contains(".mp3")
                || openFile.toString().contains(".m4a")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if (openFile.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if (openFile.toString().contains(".jpg") || openFile.toString().contains(".jpeg")
                || openFile.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if (openFile.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if (openFile.toString().contains(".3gp") || openFile.toString().contains(".mpg")
                || openFile.toString().contains(".mpeg") || openFile.toString().contains(".mpe")
                || openFile.toString().contains(".mp4") || openFile.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void playAudio(String path, String fileName, Boolean looping) {
        // set up MediaPlayer
        MediaPlayer mp = new MediaPlayer();
        mp.setLooping(looping);

        try {
            mp.setDataSource(path + File.separator + fileName);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlertWithOk(Activity activity, String title, String msg) {
        Builder alert = new Builder(activity);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setPositiveButton("OK", null);
        alert.show();
    }

    /*public static String registerForGCM(Activity activity) {
        String regid = null;
        // if(AppGlobal.checkPlayServices(activity)) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(activity);
        if (regid == null) {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(activity);
            }
            try {
                regid = gcm.register(AppConstant.SENDER_ID);
            } catch (Exception e) {
                //AppMethod.showToast(activity, e.getMessage());
                Log.e("GCM Exception", e.getMessage());
            }
        }
        // }
        return regid;
    }*/

    public static String imageToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteImg = stream.toByteArray();
        String imgString = Base64.encodeToString(byteImg,
                Base64.NO_WRAP);
        return imgString;
    }

    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "clanbook.ttf"));
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "clanbook.ttf"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void overrideBoldFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideBoldFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "clanbold.ttf"));
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "clanbold.ttf"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearApplicationData(Context context) {
        File cache = context.getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s + " DELETED");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }


        return dir.delete();
    }

    private static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        Log.e("Screenshot", "taken successfully");
        return b;

    }

    public static void setDrawableColor(View view, String colorCode) {
        Drawable background = view.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(Color.parseColor(colorCode));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(Color.parseColor(colorCode));
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(Color.parseColor(colorCode));
        }
    }

    public static int getRandomNo(int min, int max) {
        Random r = new Random();
        int randomNo = r.nextInt((max - min) + 1) + min;
        return randomNo;
    }

    public static boolean checkOnlineState(Context context, String hostURL, int timeout) {
        boolean state = false;
        ConnectivityManager CManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NInfo = CManager.getActiveNetworkInfo();
        if (NInfo != null && NInfo.isConnectedOrConnecting()) {
            try {
                if (InetAddress.getByName(hostURL).isReachable(timeout)) {
                    state = true;
                } else {
                    state = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return state;
    }

    public static String getGCMDeviceID(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String gcmDeviceId = sharedPreferences.getString(AppConstant.PREF_GCM_DEVICE_ID, "");
        return gcmDeviceId;
    }

    public static String parseDateFormat(String time, String inputPattern, String outputPattern) {
        //String inputPattern = "dd-MM-yyyy HH:mm:ss";
        //String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void showInfoDialog(Context context, String title, String msg) {
        final Dialog infoDialog = new Dialog(context);
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(R.layout.layout_backup_dialog);
        infoDialog.setCancelable(false);
        infoDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView txtMessage = (TextView) infoDialog.findViewById(R.id.txtMessage);
        TextView txtTitle = (TextView) infoDialog.findViewById(R.id.txtTitle);
        Button btnDBStatus = (Button) infoDialog.findViewById(R.id.btnDBStatus);

        txtTitle.setText(title);
        txtMessage.setText(msg);

        btnDBStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.dismiss();
            }
        });
        infoDialog.show();
    }

    public static void showInfoDialogWithClick(Context context, final Dialog infoDialog, String title, String msg, View.OnClickListener onClickListener) {
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infoDialog.setContentView(R.layout.layout_backup_dialog);
        infoDialog.setCancelable(false);
        infoDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView txtMessage = (TextView) infoDialog.findViewById(R.id.txtMessage);
        TextView txtTitle = (TextView) infoDialog.findViewById(R.id.txtTitle);
        Button btnDBStatus = (Button) infoDialog.findViewById(R.id.btnDBStatus);

        txtTitle.setText(title);
        txtMessage.setText(msg);

        btnDBStatus.setOnClickListener(onClickListener);
        infoDialog.show();
    }

    public static String readRawFileAsString(Context context, int rawFile)
            throws java.io.IOException {
        InputStream inputStream = context.getResources().openRawResource(
                rawFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuffer result = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        return result.toString();
    }

    public static String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static String extractPhoneNumber(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (i == 0 && c == '+') {
                builder.append(c);
            } else if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}