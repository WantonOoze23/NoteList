package com.tyshko.notelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tyshko.notelist.repository.NoteRepository
import com.tyshko.notelist.ui.screens.MainScreen
import com.tyshko.notelist.ui.theme.NoteListTheme
import com.tyshko.notelist.view.NoteListViewModel
import com.tyshko.notelist.view.NoteListViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: NoteListViewModel

    @Inject
    lateinit var repository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NoteApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val factory = NoteListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteListViewModel::class.java]

        setContent {
            NoteListTheme {
                val navController = rememberNavController()
                AppNavigation(navController, viewModel)
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
                onEvent = {viewModel.onEvent(it)}
            )
        }

    }
}
