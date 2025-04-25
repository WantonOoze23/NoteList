package com.tyshko.notelist.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tyshko.notelist.view.NoteEvent
import com.tyshko.notelist.view.NoteListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int?,
    viewModel: NoteListViewModel,
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    val isEnabled = state.title.isNotBlank() && state.description.isNotBlank()


    val infiniteTransition = rememberInfiniteTransition(label = "save_button_anim")
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xFF4CAF50),
        targetValue = Color(0xFF2196F3),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (noteId != null) "Edit Note" else "New Note",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = state.title,
                        onValueChange = { viewModel.onEvent(NoteEvent.SetTitle(it)) },
                        label = { Text("Title") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    TextField(
                        value = state.description,
                        onValueChange = { viewModel.onEvent(NoteEvent.SetDescription(it)) },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val current = state.date
                                DatePickerDialog(
                                    context,
                                    { _, year, month, day ->
                                        val updatedDate = current.withYear(year)
                                            .withMonth(month + 1)
                                            .withDayOfMonth(day)
                                        viewModel.onEvent(NoteEvent.SetDate(updatedDate))
                                    },
                                    current.year,
                                    current.monthValue - 1,
                                    current.dayOfMonth
                                ).show()
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Date: ${state.date.toLocalDate()}",
                            fontSize = 16.sp
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val current = state.date
                                TimePickerDialog(
                                    context,
                                    { _, hour, minute ->
                                        val updatedTime = current.withHour(hour).withMinute(minute)
                                        viewModel.onEvent(NoteEvent.SetDate(updatedTime))
                                    },
                                    current.hour,
                                    current.minute,
                                    true
                                ).show()
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select time",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Time: %02d:%02d".format(
                                state.date.hour,
                                state.date.minute
                            ),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cancel", color = Color.Black)
                }
                Button(
                    onClick = {
                        if (noteId != null) {
                            viewModel.onEvent(NoteEvent.UpdateNote)
                        } else {
                            viewModel.onEvent(NoteEvent.SaveNote)
                        }
                        navController.popBackStack()
                    },
                    enabled = isEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isEnabled) color else Color.LightGray,
                        contentColor = if (isEnabled) Color.White else Color.DarkGray
                    )
                ) {
                    Text("Save")
                }
            }
        }
    }
}

