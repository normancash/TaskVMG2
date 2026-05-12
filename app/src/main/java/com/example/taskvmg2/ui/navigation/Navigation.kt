package com.example.taskvmg2.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskListScreen
@Serializable
data class TaskDetailScreen(
    val taskId : Int
)