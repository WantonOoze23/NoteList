package com.tyshko.notelist.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tyshko.notelist.data.NoteDao
import com.tyshko.notelist.models.note.Converters
import com.tyshko.notelist.models.note.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun dao(): NoteDao

}