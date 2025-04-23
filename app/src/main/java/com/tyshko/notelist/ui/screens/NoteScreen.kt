package com.tyshko.notelist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.tyshko.notelist.ui.components.NoteDetails
import com.tyshko.notelist.view.NoteListViewModel

@Composable
fun NoteScreen(noteId: Int, viewModel: NoteListViewModel, navController: NavController) {
    val state = viewModel.state.collectAsState().value
    val note = state.notes.find { it.id == noteId }

    Column {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .background(color = Color.Black)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        NoteDetails(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            note = note
        )
    }
}

