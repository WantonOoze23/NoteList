package com.tyshko.notelist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.*

import com.tyshko.notelist.view.NoteListViewModel

@Composable
fun MainScreen(
    viewModel: NoteListViewModel,
    navHostController: NavHostController,
    onCreateClick: () -> Unit = {},
    onClick: () ->  Unit = {},
){


    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        Row(
            modifier = Modifier
                .statusBarsPadding()
        ) {
            Text(
                text = "Hello Dear name",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .background(color = Color.Black),
        shape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 30.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp,
        ),
    ) {
        item {

        }
        item {

        }
    }

}