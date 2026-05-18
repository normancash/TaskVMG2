package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.repository.TaskRepository
import com.example.taskvmg2.ui.model.Task

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    var tasks by mutableStateOf(listOf<Task>())
        private set
    var task by mutableStateOf<Task?>(null)

    init {
        loadTask()
    }
    private fun loadTask() {
        tasks = repository.getTasks()
    }
    fun addTask(task: Task) {
        repository.addTask(task)
        loadTask()
    }
    fun removeTask(task: Task) {
        repository.removeTask(task)
        loadTask()
    }
    fun toggleTask(task: Task) {
        repository.toggleTask(task)
        loadTask()
    }
    fun getTaskId(id: Int): Task? {
        return repository.getTaskId(id)
    }
}