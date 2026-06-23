package com.example.taskvmg2.ui.viewmodel

sealed interface TaskDetailEvent {
    data object Saved : TaskDetailEvent
    data class Error (
        val message: String
    ) : TaskDetailEvent
}