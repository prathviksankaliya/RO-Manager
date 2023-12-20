package com.itcraftsolution.romanager.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SpfOwnerDetails {
    private SharedPreferences spf;
    private SharedPreferences.Editor editor;

    public SpfOwnerDetails(Context context){
        spf = context.getSharedPreferences("OwnerDetails", Context.MODE_PRIVATE);
        editor = spf.edit();
    }

    public SharedPreferences setOwnerPreference(String phone){
        editor.putString("OwnerPhone", phone);
        editor.apply();
        return spf;
    }

    public SharedPreferences getOwnerPreference(){
        return spf;
    }
}
