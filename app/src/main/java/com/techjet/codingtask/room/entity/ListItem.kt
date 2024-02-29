package com.techjet.codingtask.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "listitem",
    indices = [Index(value = ["title", "image", "link"], unique = true)]
)
class ListItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "title")
    var title: String = ""

    @ColumnInfo(name = "image")
    var image: String = ""

    @ColumnInfo(name = "link")
    var link: String = ""
}
