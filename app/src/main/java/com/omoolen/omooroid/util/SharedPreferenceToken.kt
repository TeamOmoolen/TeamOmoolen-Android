package com.omoolen.omooroid.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.omoolen.omooroid.splash.SplashActivity


object SharedPreferenceToken {

    private val TOKEN = "TOKEN"
    fun putSettingItem(mContext:Context, key: String, value: String) {
        Log.d("SHARED", "Put $key (value : $value ) to $TOKEN")
        val preferences: SharedPreferences = mContext.getSharedPreferences(TOKEN, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    //USER_TOKEN
    fun getSettingItem(mContext: Context, key: String): String? {
        Log.d("SHARED", "Get $key from $TOKEN")
        return mContext.getSharedPreferences(TOKEN, 0).getString(key, null)
    }
}