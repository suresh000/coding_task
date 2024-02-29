package com.techjet.codingtask.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techjet.codingtask.room.entity.ListItem


@Dao
interface ListItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(values: List<ListItem>)

    @Query("SELECT * FROM listitem")
    fun getList(): MutableList<ListItem>

    @Query("SELECT COUNT(*) FROM listitem")
    fun getCount(): Int

}