package com.itson.notesapp.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit,
    context: Context // Add context parameter
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (validateNote(state.title.value, state.description.value, context)) {
                    onEvent(NotesEvent.SaveNote(
                        title = state.title.value,
                        description = state.description.value
                    ))
                    navController.popBackStack()
                }
            }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save Note"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.title.value,
                onValueChange = {
                    state.title.value = it.trim() // Trim whitespaces
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Title")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.description.value,
                onValueChange = {
                    state.description.value = it.trim() // Trim whitespaces
                },
                placeholder = {
                    Text(text = "Description")
                }

            )

        }
    }
}

private fun validateNote(title: String, description: String, context: Context): Boolean {
    if (title.isEmpty() || title.isBlank() || description.isEmpty() || description.isBlank()) {
        Toast.makeText(context, "No se puede guardar una nota con campos vacíos o solo con espacios en blanco.", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
}
