package com.example.taskvmg2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.repository.TaskRepository

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()
}