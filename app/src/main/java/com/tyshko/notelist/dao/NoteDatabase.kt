package com.tyshko.notelist.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tyshko.notelist.data.NoteDao
import com.tyshko.notelist.models.note.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao

}