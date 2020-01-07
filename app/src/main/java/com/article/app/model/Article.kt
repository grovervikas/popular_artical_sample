package com.article.app.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * @author vikas.grover
 * Pojo - Article
 */
data class Article(
    @SerializedName("id")
    private var _id: String,
    @SerializedName("title")
    private var _title: String,
    @SerializedName("thumbnail")
    private var _thumbnail: String,
    @SerializedName("created_at")
    private var _created_at: String,
    @SerializedName("updated_at")
    private var _updated_at: String,
    @SerializedName("about")
    private var _about: String,
    @SerializedName("author")
    private var _author: String,
    @SerializedName("images")
    private var _images: MutableList<String> = mutableListOf()
) : Serializable, BaseObservable() {

    var id: String
        @Bindable get() = _id
        set(value) {
            _id = value
        }
    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
        }

    var thumbnail: String
        @Bindable get() = _thumbnail
        set(value) {
            _thumbnail = value
        }

    var created_at: String
        @Bindable get() = _created_at
        set(value) {
            _created_at = value
        }

    var updated_at: String
        @Bindable get() = _updated_at
        set(value) {
            _updated_at = value
        }

    var about: String
        @Bindable get() = _about
        set(value) {
            _about = value
        }

    var author: String
        @Bindable get() = _author
        set(value) {
            _author = value
        }

    var images: List<String>
        @Bindable get() = _images
        set(value) {
            if (_images.isNullOrEmpty()) {
                _images = ArrayList()
                _images.addAll(value)
            } else {
                _images.clear()
                _images.addAll(value)
            }
        }
}
