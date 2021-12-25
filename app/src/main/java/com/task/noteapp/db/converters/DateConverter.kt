package com.task.noteapp.db.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Bulent Turkmen on 29.09.2021.
 */
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}