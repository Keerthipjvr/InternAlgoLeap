package com.example.algoleap

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmpDao {

    @Insert
    suspend fun insertEmpEnt(empEnt: EmpEnt)

    @Query("SELECT * FROM EmpEnt ORDER BY id DESC")
    suspend fun getAllEmpEnt(): List<EmpEnt>

    @Update
    fun updateEmpEnt(empEnt: EmpEnt)

    @Delete
    suspend fun deleteEmpEnt(entEnt: EmpEnt)


}