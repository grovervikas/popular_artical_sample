package com.article.app.netio

import com.article.app.model.Article
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author
 * Retrofit Calls
 */
interface RetrofitInterface {

    // get list of articles
    @GET(ApiConst.ARTICLE_LIST)
    fun articles(): Observable<List<Article>>
}