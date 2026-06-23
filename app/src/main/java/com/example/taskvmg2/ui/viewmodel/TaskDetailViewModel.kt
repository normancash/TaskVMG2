package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.repository.TaskRepository
import com.example.taskvmg2.ui.service.ApiResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val repository: TaskRepository
) : ViewModel()
{
    private val _state =
        MutableStateFlow<TaskDetailState>(TaskDetailState.Loading)

    private val _event = MutableSharedFlow<TaskDetailEvent>()

    val state = _state.asStateFlow()

    val event = _event.asSharedFlow()

    var id by mutableStateOf("")
       private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var completed by mutableStateOf(false)
        private set

    fun onIdChange(id: String) {
        this.id = id
    }

    fun onTitleChange(title: String) {
        this.title = title
    }

    fun onDescriptionChange(description: String) {
        this.description = description
    }

    fun onCompletedChange(completed: Boolean) {
        this.completed = completed
    }

    fun clearForm() {
        id = ""
        title = ""
        description = ""
        completed = false
    }

    fun loadForm(task : Task){
        id = task.id
        title = task.title
        description = task.description
        completed = task.completed
    }

    fun findById(id: String) {
        if (id == "0") {
            clearForm()
            return
        }
        _state.value = TaskDetailState.Loading

        viewModelScope.launch {
            when (val result = repository.findById(id))
            {
                is ApiResult.Success-> {
                    loadForm(result.data)
                    _state.value = TaskDetailState.Success(result.data)
                }
                is ApiResult.Error-> {
                    _state.value = TaskDetailState.Error(result.message)
                }
            }
        }
    }

    fun save() {
        val task = Task(
            id = id,
            title = title,
            description = description,
            completed = completed
        )
        _state.value = TaskDetailState.Loading
        viewModelScope.launch {
            when (val result = repository.save(task)) {
                is ApiResult.Success -> {
                    _event.emit(TaskDetailEvent.Saved)
                }
                is ApiResult.Error -> {
                    _event.emit(TaskDetailEvent.Error(result.message))
                }
            }
        }
    }
}