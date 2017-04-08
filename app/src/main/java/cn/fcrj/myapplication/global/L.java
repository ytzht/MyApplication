package cn.fcrj.myapplication.global;

import android.util.Log;

/**
 * Created by zht on 2017/04/07 10:31
 */

public class L {

    public static final String TAG = "=====";
    public static final boolean DEBUG = true;


    public static void d(String msg){
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String TAG, String msg){
        if (DEBUG) {
            Log.d(TAG + L.TAG, msg);
        }
    }

    public static void e(String msg){
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String TAG, String msg){
        if (DEBUG) {
            Log.e(TAG + L.TAG, msg);
        }
    }







}
