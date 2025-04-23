package com.tyshko.notelist.models.state

import com.tyshko.notelist.models.note.Note
import java.time.LocalDateTime

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val description: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val noteToEdit: Note? = null,
    val isAddingNew: Boolean = false,
)
