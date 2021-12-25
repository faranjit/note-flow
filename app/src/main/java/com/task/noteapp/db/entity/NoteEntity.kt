package com.task.noteapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    val id: Long? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "desc")
    val desc: String? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "created_on")
    val createDate: Date? = Calendar.getInstance().time
)