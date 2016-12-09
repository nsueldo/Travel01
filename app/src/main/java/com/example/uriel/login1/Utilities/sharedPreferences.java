package com.example.uriel.login1.Utilities;

import android.content.Context;
import android.content.SharedPreferences;



public class sharedPreferences {
    static public void saveLogin (Context context, String user, String pass){
        SharedPreferences sp = context.getSharedPreferences("Login", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("USER",user );
        editor.putString("PASSWORD",pass);
        editor.commit();
    }

    static public String[] getLogin (Context context){
        String[] data = {null,null};
        SharedPreferences sp1= context.getSharedPreferences("Login",0);
        data[0] = sp1.getString("USER", null);
        data[1] = sp1.getString("PASSWORD", null);
        return data;
    }

    static public void saveSettings (Context context, boolean statusNotify){
        SharedPreferences sp = context.getSharedPreferences("Settings", 1);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("NOTIFICATIONS", statusNotify);
        editor.commit();
    }

    static public boolean getSettings (Context context){
        boolean isChecked;
        SharedPreferences sp = context.getSharedPreferences("Settings", 1);
        isChecked = sp.getBoolean("NOTIFICATIONS", false);
        return isChecked;
    }


}
