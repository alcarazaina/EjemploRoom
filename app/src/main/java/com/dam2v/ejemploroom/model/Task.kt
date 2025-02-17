package com.dam2v.ejemploroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Esto es lo que correspondería a una tabla que se llamaría "tasks"
// Lo que la distingue como tal es la anotación @Entity
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // integer id autoincrement primary key
    val title: String,
    val description: String,
    val completed: Boolean
)
