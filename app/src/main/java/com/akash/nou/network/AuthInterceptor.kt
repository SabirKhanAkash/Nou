package com.akash.nou.network

import Constant
import com.akash.nou.service.EncryptedSharedPrefService
import com.akash.nou.service.SharedPrefService
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(
    private val sharedPrefService: SharedPrefService,
    private val encryptedSharedPrefService: EncryptedSharedPrefService
) : Interceptor {

    private fun refreshToken(chain: Chain, request: Request): Response {
        val refreshToken = encryptedSharedPrefService.getString(Constant.ESP_REFRESH_TOKEN_KEY)
        val response =
            RetrofitProvider.refreshTokenApiService().refreshAccessToken(refreshToken).execute()
        return if (response.isSuccessful) {
            if (response.code() == 403 || response.code() == 401) {
                chain.proceed(request)
            }
            else {
                val token = response.body()?.accessToken ?: ""
                val refreshToken = response.body()?.refreshToken ?: ""
                sharedPrefService.setString(Constant.SP_ACCESS_TOKEN_KEY, token)
                encryptedSharedPrefService.setString(Constant.ESP_REFRESH_TOKEN_KEY, refreshToken)

                val newRequest = request
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }

        }
        else {
            chain.proceed(request)
        }
    }

    override fun intercept(chain: Chain): Response {
        val token = sharedPrefService.getString(Constant.SP_ACCESS_TOKEN_KEY)
        val request = chain.request()
        if (token.isNotEmpty()) {
            val newRequest = request
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            val response = chain.proceed(newRequest)
            return if (response.code == 401) {
                response.close()
                refreshToken(chain, request)
            }
            else {
                response
            }
        }
        else {
            return refreshToken(chain, request)
        }
    }
}