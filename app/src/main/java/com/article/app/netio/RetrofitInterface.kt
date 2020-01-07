package com.article.app.netio

import com.article.app.model.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author
 * Retrofit Calls
 */
interface RetrofitInterface {

    // get list of articles
    @GET(ApiConst.ARTICLE_LIST)
    fun articles(): Observable<List<Article>>

    // get article Item
    @GET(ApiConst.IMAGES_LIST)
    fun images(): Observable<List<Article>>

    // get article Item
    @GET(ApiConst.ARTICLE_ITEM.plus("{itemIndex}"))
    fun articleItem(@Path("itemIndex") itemIndex : String): Observable<Article>
}