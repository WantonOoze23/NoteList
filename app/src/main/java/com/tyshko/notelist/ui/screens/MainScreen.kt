package com.tyshko.notelist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            item{
                val testNote: Note = Note(1, "Dear", "Lorehm ipsum")
                NoteCard(testNote, modifier = Modifier) { }
            }


        }
    }
}