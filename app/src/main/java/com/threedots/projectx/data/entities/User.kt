package com.threedots.projectx.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class User(
    @PrimaryKey(autoGenerate = true)
    val user_id: Int,
    val user_access_token: String
)
