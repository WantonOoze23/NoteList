package com.tyshko.notelist.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tyshko.notelist.view.NoteEvent
import com.tyshko.notelist.view.NoteListViewModel

@Composable
fun AddEditNoteScreen(
    noteId: Int?,
    viewModel: NoteListViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value
    val isEditing = noteId != null



}
