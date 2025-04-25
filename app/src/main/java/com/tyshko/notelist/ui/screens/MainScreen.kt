package com.tyshko.notelist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.*
import com.tyshko.notelist.ui.components.NoteCard
import com.tyshko.notelist.view.NoteEvent

import com.tyshko.notelist.view.NoteListViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: NoteListViewModel,
    navHostController: NavHostController,
){
    val state = viewModel.state.collectAsState().value

    val sortedNotes = state.notes.sortedByDescending { it.date }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
        floatingActionButton = {
            val infiniteTransition = rememberInfiniteTransition(label = "fab_animation")

            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 3000),
                    repeatMode = RepeatMode.Restart
                ),
                label = "rotation"
            )

            val color by infiniteTransition.animateColor(
                initialValue = Color(0xFF4CAF50),
                targetValue = Color(0xFF2196F3),
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 3000),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "color"
            )

            FloatingActionButton(
                onClick = {
                    viewModel.clearNoteInput()
                    navHostController.navigate("addEdit")
                },
                containerColor = color,
                modifier = Modifier
                    .graphicsLayer {
                        rotationZ = rotation
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Black)
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hello dear Noter",
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )
                }
            }

            items(sortedNotes.size) { index ->
                val note = sortedNotes[index]
                var visible by remember(note.id) { mutableStateOf(false) }

                LaunchedEffect(key1 = note.id) {
                    delay(index * 50L)
                    visible = true
                }

                val alpha = if (visible) 1f else 0f
                val offsetY = if (visible) 0f else 40f

                NoteCard(
                    note = note,
                    onDeleteClick = {
                        viewModel.onEvent(NoteEvent.DeleteNote(note))
                    },
                    onClick = {
                        viewModel.onEvent(NoteEvent.EditNote(note))
                        navHostController.navigate("addEdit?noteId=${note.id}")
                    },
                    modifier = Modifier
                        .graphicsLayer {
                            this.alpha = alpha
                            this.translationY = offsetY
                        }
                        .animateItemPlacement(tween(300))
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }
        }
    }
}