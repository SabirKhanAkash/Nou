/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

import com.akash.nou.BuildConfig
import com.akash.nou.api.AuthAPI
import com.akash.nou.api.TicketAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val builder = OkHttpClient.Builder()
    private val interceptor = HttpLoggingInterceptor()

    private fun getRetrofitInstance(): Retrofit {

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        builder.callTimeout(5, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)
        builder.readTimeout(5, TimeUnit.SECONDS)
        builder.writeTimeout(5, TimeUnit.SECONDS)

        val gson: Gson = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        if (retrofit == null) {
            System.setProperty("javax.net.debug", "all");
            retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.dispatcher(dispatcher).build()).build()
        }

        return retrofit!!
    }

    fun getAuthInterfaceService(): AuthAPI {
        return getRetrofitInstance().create(AuthAPI::class.java)
    }

    fun getTicketInterfaceService(): TicketAPI {
        return getRetrofitInstance().create(TicketAPI::class.java)
    }
}