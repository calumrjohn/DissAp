package com.example.dissap

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HuntDao {
    @Insert
    fun insert(h: Hunt):Long
    @Delete
    fun deleteHunt(h: Hunt)
    @Query("SELECT huntId FROM hunt WHERE huntId = :hId ")
    fun searchByUsername(hId: String): List<Long>
    @Query("SELECT * FROM hunt ORDER BY huntId ASC")
    fun getAll() : List<Hunt>

}

