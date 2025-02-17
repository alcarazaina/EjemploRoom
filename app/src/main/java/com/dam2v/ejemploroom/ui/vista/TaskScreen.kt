package com.dam2v.ejemploroom.ui.vista

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dam2v.ejemploroom.model.Task
import com.dam2v.ejemploroom.model.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel) {
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())

    Column {
        TaskList(tasks, onDelete = { viewModel.delete(it) })
        AddTaskButton { title, description ->
            viewModel.insert(Task(title = title, description = description, completed = false))
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, onDelete: (Task) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onDelete)
        }
    }
}

@Composable
fun TaskItem(task: Task, onDelete: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDelete(task) },
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.titleLarge)
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AddTaskButton(onAdd: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") }
        )
        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAdd(title, description)
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Agregar Tarea")
        }
    }
}