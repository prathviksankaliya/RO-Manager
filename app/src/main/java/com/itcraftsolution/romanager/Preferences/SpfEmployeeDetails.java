package com.itcraftsolution.romanager.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.ConcurrentHashMap;

import kotlinx.coroutines.flow.SharedFlow;

public class SpfEmployeeDetails {
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;


    public SpfEmployeeDetails(Context context){
        spf = context.getSharedPreferences("EmployeeDetails", Context.MODE_PRIVATE);
        editor = spf.edit();
    }

    public SharedPreferences setEmployeePreference(String phone){
        editor.putString("EmployeePhone", phone);
        editor.apply();
        return spf;
    }
}
