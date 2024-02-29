package com.techjet.codingtask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.techjet.codingtask.room.dao.ListItemDao
import com.techjet.codingtask.room.entity.ListItem

@Database(entities = [ListItem::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun listItemDao(): ListItemDao

    companion object {

        private const val DB_NAME = "coding_test"
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            if (INSTANCE == null) {
                synchronized(AppRoomDatabase::class.java) {
                    INSTANCE = databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java, DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        fun refreshInstance(context: Context) {
            INSTANCE = null
            getInstance(context)
        }

    }
}
