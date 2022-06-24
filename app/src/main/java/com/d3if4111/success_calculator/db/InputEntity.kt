package com.d3if4111.success_calculator.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input")
data class InputEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var nama: String,
)