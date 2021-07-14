package com.omoolen.omooroid.util

import android.content.Context
import android.preference.PreferenceManager
import android.provider.Settings.Global.putString
import com.omoolen.omooroid.search.fragment.one.recycle.recent.RecentInfo
import org.json.JSONArray
import org.json.JSONException


object SharedPreferences {

    fun setStringArrayPref(context: Context, key: String, values: MutableList<RecentInfo>) {
        val prefs = context.getSharedPreferences("setting",Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val a = JSONArray()
        for (i in 0 until values.size) {
            a.put(values[i].name)
        }
        if (values.isNotEmpty()) {
            editor.putString(key, a.toString())
        } else {
            editor.putString(key, null)
        }
        editor.apply()
    }

    fun getStringArrayPref(context: Context, key: String): ArrayList<String>? {
        val prefs = context.getSharedPreferences("setting",Context.MODE_PRIVATE)
        val json = prefs.getString(key, null)
        val urls = ArrayList<String>()
        if (json != null) {
            try {
                val a = JSONArray(json)
                for (i in 0 until a.length()) {
                    val url = a.optString(i)
                    urls.add(url)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return urls
    }


}