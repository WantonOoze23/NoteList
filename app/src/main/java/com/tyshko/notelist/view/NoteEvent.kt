package com.tyshko.notelist.view

import com.tyshko.notelist.models.note.Note
import java.time.LocalDateTime

sealed interface NoteEvent {
    data object SaveNote: NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetDescription(val description: String): NoteEvent
    data class SetDate(val date: LocalDateTime): NoteEvent
    data class DeleteNote(val note: Note): NoteEvent
    data class EditNote(val note: Note): NoteEvent
    data object UpdateNote : NoteEvent
}