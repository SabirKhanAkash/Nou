/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

import com.akash.nou.BuildConfig
import com.akash.nou.api.AuthAPI
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

        builder.callTimeout(50, TimeUnit.SECONDS)
        builder.connectTimeout(50, TimeUnit.SECONDS)
        builder.readTimeout(50, TimeUnit.SECONDS)
        builder.writeTimeout(50, TimeUnit.SECONDS)

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
}