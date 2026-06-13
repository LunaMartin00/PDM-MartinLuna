package com.example.atry.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.atry.model.Task
import com.example.atry.ui.components.CreateTask
import com.example.atry.ui.components.TaskCard
import com.example.atry.viewmodel.GeneralViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: GeneralViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val tasks = viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tasks list") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(tasks.value) { task ->
                TaskCard(task)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        if (showDialog) {
            CreateTask(
                onDismiss = { showDialog = false },
                onTaskCreated = { newTitle, newDescription ->
                    val newTask = Task(
                        title = newTitle,
                        description = newDescription
                    )
                    viewModel.addTask(newTask)
                    showDialog = false
                }
            )
        }
    }
}