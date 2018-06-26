package com.work.hany.mosquitoproject.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MosquitoRetrofit private constructor() {

    private object Holder {
        val INSTANCE: Retrofit
            get() { return Retrofit.Builder()
                        .baseUrl(StringBuffer().append(MosquitoRetrofit.baseURL).append("/").toString())
                        .client(createOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create()).build() }


        private fun createOkHttpClient(): OkHttpClient { //데이터 로그찍는것.
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            return builder.build()
        }
    }

    companion object {
        private const val baseURL = "http://openapi.seoul.go.kr:8088"
        val instance: Retrofit by lazy {
            Holder.INSTANCE
        }

    }

}