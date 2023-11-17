package com.project.sameer.DataClass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "drinks")
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val itemid: String,
    val img: String,
    val discription: String,
    val alchole: String,
)
