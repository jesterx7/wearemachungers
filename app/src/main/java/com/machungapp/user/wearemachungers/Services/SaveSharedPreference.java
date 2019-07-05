package com.machungapp.user.wearemachungers.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_NAME= "username";
    static final String PASSWORD = "password";
    static final String NIM = "nim_user";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setPassword(Context ctx, String password)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public static String getPassword(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PASSWORD, "");
    }

    public static void setNim(Context ctx, String nim)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(NIM, nim);
        editor.commit();
    }

    public static String getNim(Context ctx)
    {
        return getSharedPreferences(ctx).getString(NIM, "");
    }
}
