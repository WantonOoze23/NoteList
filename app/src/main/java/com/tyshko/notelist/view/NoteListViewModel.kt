package com.tyshko.notelist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tyshko.notelist.data.NoteDao
import com.tyshko.notelist.models.state.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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