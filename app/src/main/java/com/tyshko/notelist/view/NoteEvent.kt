package com.tyshko.notelist.view

import com.tyshko.notelist.models.note.Note
import java.time.LocalDateTime

sealed interface NoteEvent {
    object SaveNote: NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetDescription(val description: String): NoteEvent
    data class SetDate(val date: LocalDateTime): NoteEvent
    object ShowDialog: NoteEvent
    object HideDialog: NoteEvent
    data class DeleteNote(val note: Note): NoteEvent
}