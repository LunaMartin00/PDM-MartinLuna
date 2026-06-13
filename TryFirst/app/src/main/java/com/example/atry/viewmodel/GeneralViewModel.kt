package com.example.atry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.atry.data.TaskDao
import com.example.atry.model.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GeneralViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }

    class Factory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GeneralViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GeneralViewModel(taskDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}