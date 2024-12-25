/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class EncryptedSharedPreference {

    private val PREFS_NAME = "spNouTinkEncrypted"
    private val KEYSET_NAME = "tink_keyset"
    private val PREF_KEYSET = "spNouTinkEncrypted_pref"

    init {
        AeadConfig.register()
    }

    private fun getAead(context: Context): Aead {
        val keysetHandle: KeysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, KEYSET_NAME, PREF_KEYSET)
            .withKeyTemplate(com.google.crypto.tink.aead.AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
        return keysetHandle.getPrimitive(Aead::class.java)
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun encrypt(context: Context, plaintext: String): ByteArray {
        val aead = getAead(context)
        return aead.encrypt(plaintext.toByteArray(), null)
    }

    private fun decrypt(context: Context, ciphertext: ByteArray): String {
        val aead = getAead(context)
        return String(aead.decrypt(ciphertext, null))
    }

    fun setString(context: Context, key: String, value: String) {
        val encryptedValue = encrypt(context, value)
        val prefs = getSharedPreferences(context)
        prefs.edit().putString(key, encryptedValue.toBase64()).apply()
    }

    fun getString(context: Context, key: String): String? {
        val prefs = getSharedPreferences(context)
        val encryptedValue = prefs.getString(key, null) ?: return null
        return decrypt(context, encryptedValue.decodeBase64())
    }

    fun clearDataOnKey(context: Context, key: String) {
        val prefs = getSharedPreferences(context)
        prefs.edit().remove(key).apply()
    }

    fun clearData(context: Context) {
        val prefs = getSharedPreferences(context)
        prefs.edit().clear().apply()
    }

    private fun ByteArray.toBase64(): String =
        android.util.Base64.encodeToString(this, android.util.Base64.DEFAULT)

    private fun String.decodeBase64(): ByteArray =
        android.util.Base64.decode(this, android.util.Base64.DEFAULT)
}
