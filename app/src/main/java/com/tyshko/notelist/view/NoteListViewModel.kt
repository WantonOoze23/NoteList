package com.tyshko.notelist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyshko.notelist.models.note.Note
import com.tyshko.notelist.models.state.NoteState
import com.tyshko.notelist.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state: StateFlow<NoteState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getNotes().collect { notes ->
                _state.update { it.copy(notes = notes) }
            }
        }
    }


    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch{
                    repository.deleteNote(event.note)
                }
            }
            NoteEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingNew = false
                )
                }
            }
            NoteEvent.SaveNote -> {
                val title = _state.value.title
                val description = _state.value.description
                val date = _state.value.date
                val existingNote = _state.value.noteToEdit

                if (title.isBlank() || description.isBlank()) return

                val note = if (existingNote != null) {
                    existingNote.copy(
                        title = title,
                        description = description,
                        date = date
                    )
                } else {
                    Note(
                        title = title,
                        description = description,
                        date = date
                    )
                }

                viewModelScope.launch {
                    repository.insertNote(note) // insert працює як add/update
                }

                _state.update {
                    it.copy(
                        isAddingNew = false,
                        title = "",
                        description = "",
                        date = LocalDateTime.now(),
                        noteToEdit = null
                    )
                }
            }

            is NoteEvent.SetDate -> {
                _state.update { it.copy(
                    date = event.date
                ) }
            }
            is NoteEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is NoteEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
            NoteEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingNew = true
                )
                }
            }

            is NoteEvent.EditNote -> {
                _state.update {
                    it.copy(
                        title = event.note.title,
                        description = event.note.description,
                        date = event.note.date,
                        isAddingNew = true,
                        noteToEdit = event.note
                    )
                }
            }
        }
    }
}