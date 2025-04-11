package com.tyshko.notelist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyshko.notelist.data.NoteDao
import com.tyshko.notelist.models.note.Note
import com.tyshko.notelist.models.state.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class NoteListViewModel(
    private val dao: NoteDao
): ViewModel() {
    companion object{

    }

    private val _state = MutableStateFlow(NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch{
                    dao.deleteNote(event.note)
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

                if (title.isBlank() || description.isBlank())
                    return

                val note = Note(
                    title = title,
                    description = description,
                    date = date
                )

                viewModelScope.launch {
                    dao.upsertNote(note)
                }

                _state.update { it.copy(
                    isAddingNew = false,
                    title = "",
                    description = "",
                    date = LocalDateTime.now()
                ) }
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
        }
    }

}