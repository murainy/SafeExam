package com.murainy.safeexam.Shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by murainy on 2015/12/13.
 */
public class OperateShareprefrence {


    public static void storeShareprefrence(Context context, String account, String password,boolean auto) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user", 0).edit();
        editor.putString("account", account);
        editor.putString("password", password);
        editor.putBoolean("autologin", auto);
        editor.commit();
    }

    public static Account loadShareprefrence(Context context) {
        Account account = new Account();
        SharedPreferences pref = context.getSharedPreferences("user", 0);
        account.setAccount(pref.getString("account", ""));
        account.setPassword(pref.getString("password", ""));
        account.setAutologin(pref.getBoolean("autologin",true));
        return account;
    }

    public static void deleteShareprefrence(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("user", 0).edit();
        editor.clear();
        editor.commit();
    }

}
