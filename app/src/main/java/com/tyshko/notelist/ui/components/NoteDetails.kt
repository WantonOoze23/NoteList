package com.tyshko.notelist.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyshko.notelist.models.note.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteDetails(modifier: Modifier = Modifier, note: Note?) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    if (note != null) {
        Column(
            modifier = modifier.padding(vertical = 16.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = note.title,
                fontSize = 30.sp,
                color = Color.Black
            )
            Text(
                text = note.description,
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = modifier.padding(top = 10.dp)
            )
            Text(
                text = note.date.format(formatter),
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = modifier.padding(top = 10.dp)
            )
        }
    } else {
        Text("Note not found")
    }
}