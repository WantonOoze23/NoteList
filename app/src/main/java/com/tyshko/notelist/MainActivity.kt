package com.tyshko.notelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tyshko.notelist.ui.screens.MainScreen
import com.tyshko.notelist.ui.theme.NoteListTheme
import com.tyshko.notelist.view.NoteListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val noteListModel = ViewModelProvider(this)[NoteListViewModel::class.java]

        setContent {
            NoteListTheme {
                Surface(

                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController, noteListModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: NoteListViewModel){
    NavHost(navController, startDestination = "main"){
        composable("main"){
            MainScreen(
                viewModel,
                navController,
                onCreateClick={},
                onClick={}
            )
        }
    }
}
