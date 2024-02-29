package com.techjet.codingtask.model.list

import com.google.gson.annotations.SerializedName


data class Item(

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("link")
    var link: String = "",

    @SerializedName("media")
    var media: Media? = null,

    @SerializedName("date_taken")
    var dateTaken: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("published")
    var published: String = "",

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("author_id")
    var authorId: String? = null,

    @SerializedName("tags")
    var tags: String? = null

)