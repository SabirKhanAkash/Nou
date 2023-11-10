/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

package com.akash.nou.utils

import android.content.Context
import com.akash.nou.model.User
import com.google.gson.Gson

class SharedPref {
    private val PREFS_NAME = "spNou"

    fun getBoolean(context: Context, key: String?): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(key, false)
    }

    fun setBoolean(context: Context, key: String?, status: Boolean?) {
        val prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putBoolean(key, status!!)
        editor.apply()
    }

    fun getString(context: Context, key: String?): String? {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(key, "")
    }

    fun setString(context: Context, key: String?, value: String?) {
        val prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getInt(context: Context, key: String?): Int {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getInt(key, 0)
    }

    fun setInt(
        context: Context, key: String?,
        value: Int
    ) {
        val prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getLong(context: Context, key: String?): Long {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getLong(key, 0)
    }

    fun setLong(context: Context, key: String?, value: Long) {
        val prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun setUser(context: Context, key: String?, obj: User?) {
        val prefs = context.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
        val editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(obj)
        editor.putString(key, json)
        editor.apply()
    }

    fun getUser(context: Context, key: String?): User {
        val gson = Gson()
        return gson.fromJson(
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(key, ""),
            User::class.java
        )
    }

    fun clearData(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun clearDataOnKey(context: Context, key: String?) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }
}