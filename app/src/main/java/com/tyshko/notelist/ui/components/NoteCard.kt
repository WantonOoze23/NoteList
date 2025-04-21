package com.tyshko.notelist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tyshko.notelist.models.note.Note
import kotlin.random.Random


@Composable
fun NoteCard(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit
) {
    val backgroundColor = remember(note.id) {
        Color(
            red = Random.nextInt(256),
            green = Random.nextInt(256),
            blue = Random.nextInt(256),
            alpha = 255
        )
    }

    Column(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .background(color = backgroundColor)
    ) {
        Text(text = note.title)
        Text(text = note.description)
        Text(text = note.date.toString())
    }
}