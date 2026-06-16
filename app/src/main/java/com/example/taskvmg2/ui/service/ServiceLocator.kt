package com.example.taskvmg2.ui.service

import com.example.taskvmg2.ui.repository.TaskRepository

object ServiceLocator {
    private val taskApi
            = RetrofitClient.instance

    val taskRepository =
        TaskRepository(taskApi)
}