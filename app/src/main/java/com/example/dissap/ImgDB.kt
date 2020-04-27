package com.example.dissap

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase
import androidx.room.Database



/* Construct the Room Database using the User and Online Game
 * entities, and their respective DAOs. */
@Database(entities = [Img::class, Hunt::class], version = 1)
abstract class ImgDB : RoomDatabase() {
    abstract fun imgDao(): ImgDao
    companion object {
        private var INSTANCE: ImgDB? = null
        fun getDatabase(context: Context): ImgDB? {
            if (INSTANCE == null) {
                synchronized(ImgDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        ImgDB::class.java, "dissap.img.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
