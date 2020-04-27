package com.example.dissap

import android.content.Context
import androidx.room.*

/* Construct the Room Database using the User and Online Game
 * entities, and their respective DAOs. */
@Database(entities = arrayOf(Hunt::class), version = 1)
abstract class HuntDB : RoomDatabase() {
    abstract fun huntDao(): HuntDao
    companion object {
        private var INSTANCE: HuntDB? = null
        fun getDatabase(context: Context): HuntDB? {
            if (INSTANCE == null) {
                synchronized(HuntDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        HuntDB::class.java, "dissap.hunt.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}