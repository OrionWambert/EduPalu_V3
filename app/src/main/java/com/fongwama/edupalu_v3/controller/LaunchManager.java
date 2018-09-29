package com.fongwama.edupalu_v3.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class LaunchManager {
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "gadgetcreek";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public LaunchManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }
    public static boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
