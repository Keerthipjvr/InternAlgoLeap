package com.example.algoleap

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EmpEnt")
data class EmpEnt(
    @ColumnInfo(name = "employeeName")
    var employeeName: String,
    @ColumnInfo(name = "employeeId")
    var employeeId: String,
    @ColumnInfo(name = "employeeEmail")
    var employeeEmail: String
):java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}