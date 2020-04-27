package com.example.dissap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity
data class Hunt(
    var title: String,
    var victory: String,
    var names: String,
    var hints: String,
    var infos: String,
    var lats: String,
    var longs: String){
    @PrimaryKey(autoGenerate = true)
    var huntId: Long? = null
}

    @Entity(foreignKeys = arrayOf(
        ForeignKey(entity = Hunt::class,
        parentColumns = arrayOf("huntId"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE)
    ))

    data class Img(

        @ColumnInfo(name = "parentId", index = true)
        val parentId: Long,
        @ColumnInfo(name = "image_Storage",typeAffinity = ColumnInfo.BLOB)
        val imageStorage: ByteArray
    ){
        @PrimaryKey(autoGenerate = true)
        var imgId: Long? = null
    }
