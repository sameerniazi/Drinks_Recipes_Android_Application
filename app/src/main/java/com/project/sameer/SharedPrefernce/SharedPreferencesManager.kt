package com.project.sameer.SharedPrefernce

import android.content.Context
import android.content.SharedPreferences
import com.project.sameer.Constant

class SharedPreferencesManager(context: Context) {

    private val PREFS_NAME = "MyPrefs"
    private val KEY_USERNAME = "group"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun savegroup(group: String) {
        editor.putString(KEY_USERNAME, group)
        editor.apply()
    }

    fun getgroup(): String? {
        return sharedPreferences.getString(KEY_USERNAME, Constant.byname)
    }
}