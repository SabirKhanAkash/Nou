/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

package com.akash.nou.service

import Constant
import android.content.Context
import android.content.SharedPreferences
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager

class EncryptedSharedPrefService private constructor(context: Context) {
    init {
        AeadConfig.register()
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            Constant.ESP_NAME, Context
                .MODE_PRIVATE
        )

    private val aead: Aead by lazy {
        val keysetHandle: KeysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, Constant.ESP_KEYSET_NAME, Constant.ESP_KEYSET_VALUE)
            .withKeyTemplate(com.google.crypto.tink.aead.AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
        keysetHandle.getPrimitive(Aead::class.java)
    }

    companion object {
        @Volatile
        private var instance: EncryptedSharedPrefService? = null

        fun getInstance(context: Context): EncryptedSharedPrefService {
            return instance ?: synchronized(this) {
                instance ?: EncryptedSharedPrefService(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }

    private fun encrypt(plaintext: String): ByteArray {
        return aead.encrypt(plaintext.toByteArray(), null)
    }

    private fun decrypt(ciphertext: ByteArray): String {
        return String(aead.decrypt(ciphertext, null))
    }

    fun setString(key: String, value: String) {
        val encryptedValue = encrypt(value)
        sharedPreferences.edit().putString(key, encryptedValue.toBase64()).apply()
    }

    fun getString(key: String): String? {
        val encryptedValue = sharedPreferences.getString(key, null) ?: return null
        return decrypt(encryptedValue.decodeBase64())
    }

    fun clearDataOnKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    private fun ByteArray.toBase64(): String =
        android.util.Base64.encodeToString(this, android.util.Base64.DEFAULT)

    private fun String.decodeBase64(): ByteArray =
        android.util.Base64.decode(this, android.util.Base64.DEFAULT)
}

