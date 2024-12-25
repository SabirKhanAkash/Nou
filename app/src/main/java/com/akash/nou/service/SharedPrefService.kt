/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.service

import Constant
import android.content.Context
import android.content.SharedPreferences
import com.akash.nou.model.User
import com.google.gson.Gson

class SharedPrefService private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constant.SP_NAME, Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: SharedPrefService? = null

        fun getInstance(context: Context): SharedPrefService {
            return instance ?: synchronized(this) {
                instance ?: SharedPrefService(context.applicationContext).also { instance = it }
            }
        }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setBoolean(key: String, status: Boolean = false) {
        val editor = sharedPreferences.edit().putBoolean(key, status).apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun setString(key: String, value: String = "") {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun setUser(key: String, obj: User?) {
        sharedPreferences.edit().putString(key, Gson().toJson(obj)).apply()
    }

    fun getUser(key: String): User? {
        return Gson().fromJson(
            sharedPreferences.getString(key, ""),
            User::class.java
        )
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun clearDataOnKey(key: String?) {
        sharedPreferences.edit().remove(key).apply()
    }
}