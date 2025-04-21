package com.tyshko.notelist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.*
import com.tyshko.notelist.models.note.Note
import com.tyshko.notelist.ui.components.NoteCard
import com.tyshko.notelist.view.NoteEvent

import com.tyshko.notelist.view.NoteListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: NoteListViewModel,
    navHostController: NavHostController,
    onEvent: (NoteEvent) -> Unit
){
    val state = viewModel.state.collectAsState().value
    Scaffold(
        modifier = Modifier
            .statusBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(onClick = {onEvent(NoteEvent.ShowDialog)}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note"
                )
            }
        }
    ) { padding ->
        LazyColumn {
            item {
                Column(
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello dear Noter",
                    color = Color.White,
                    fontSize = 20.sp,
                    )
                }
            }
            items(state.notes.size) { index ->
                val note = state.notes[index]
                NoteCard(note = note,
                    onDeleteClick = { viewModel.onEvent(NoteEvent.DeleteNote(note)) },
                    onEditClick = { viewModel.onEvent(NoteEvent.EditNote(note)) }
                )
            }
            item{
                val testNote = Note(1, "Dear", "Lorehm ipsum")
                NoteCard(testNote, modifier = Modifier, onEditClick = {}, onDeleteClick = {})
            }
        }
    }

    if (state.isAddingNew) {
        AlertDialog(
            onDismissRequest = {
                onEvent(NoteEvent.HideDialog)
            },
            confirmButton = {
                Button(onClick = {
                    onEvent(NoteEvent.SaveNote)
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onEvent(NoteEvent.HideDialog)
                }) {
                    Text("Cancel")
                }
            },
            title = { Text("Add Note") },
            text = {
                Column {
                    TextField(
                        value = state.title,
                        onValueChange = { onEvent(NoteEvent.SetTitle(it)) },
                        label = { Text("Title") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = state.description,
                        onValueChange = { onEvent(NoteEvent.SetDescription(it)) },
                        label = { Text("Description") }
                    )
                }
            }
        )
    }
}