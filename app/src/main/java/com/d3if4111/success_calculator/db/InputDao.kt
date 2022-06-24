package com.d3if4111.success_calculator.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InputDao {
    @Insert
    fun insert(input: InputEntity)

    @Query("SELECT * FROM input ORDER BY id DESC")
    fun getLastQuotes(): LiveData<List<InputEntity?>>

    @Query("DELETE FROM input")
    fun clearData()
}