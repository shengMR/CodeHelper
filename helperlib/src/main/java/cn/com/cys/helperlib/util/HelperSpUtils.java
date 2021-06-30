package cn.com.cys.helperlib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences 工具类
 * 作用：简单SP存储
 */
public class HelperSpUtils {

    private static final String DEFAULT_NAME = "sp_file";

    private static Context context;

    public static void init(Context context) {
        HelperSpUtils.context = context.getApplicationContext();
    }

    private static void checkContextAndThrow() {
        if (context == null) {
            throw new NullPointerException("需要先使用 SpUtils.init(context)");
        }
    }

    //region String
    public static void putStringValue(String key, String value) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String getStringValue(String key) {
        return getStringValue(key, "");
    }

    public static String getStringValue(String key, String defaultValue) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }
    //endregion

    //region Int
    public static void putIntValue(String key, int value) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getIntValue(String key) {
        return getIntValue(key, 0);
    }

    public static int getIntValue(String key, int defaultValue) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }
    //endregion

    //region Long
    public static void putLongValue(String key, Long value) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public static long getLongValue(String key) {
        checkContextAndThrow();
        return getLongValue(key, 0L);
    }

    public static long getLongValue(String key, long defaultValue) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }
    //endregion

    //region Boolean
    public static void putBooleanValue(String key, boolean value) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static boolean getBooleanValue(String key) {
        checkContextAndThrow();
        return getBooleanValue(key, false);
    }

    public static boolean getBooleanValue(String key, boolean defaultValue) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    //endregion

    //region Float
    public static void putFloatValue(String key, float value) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.putFloat(key, value);
        edit.commit();
    }

    public static float getFloatValue(String key) {
        checkContextAndThrow();
        return getFloatValue(key, 0f);
    }

    public static float getFloatValue(String key, float defaultValue) {
        checkContextAndThrow();
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                DEFAULT_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }
    //endregion
}
