package com.article.app.netio

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author
 * Base Api Client
 */
class BaseApiClient {

    companion object {

        fun getClient(url: String) = getBuilder(url).build()

        fun getBuilder(url: String): Retrofit.Builder {
            val gson = GsonBuilder().setLenient().disableHtmlEscaping().create()
            val builder = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
            return builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }
    }
}