package com.article.app.model

import java.io.Serializable


/**
 * @author vikas.grover
 * Pojo - Article
 */
data class Article(
    val id: String = "",
    val title: String = "",
    val thumbnail: String = "",
    val created_at: String = "",
    val updated_at: String = "",
    val about: String = "",
    val author: String = "") : Serializable