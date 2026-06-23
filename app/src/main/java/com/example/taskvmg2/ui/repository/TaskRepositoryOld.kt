package com.example.taskvmg2.ui.repository

import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.service.ApiResult
import com.example.taskvmg2.ui.service.RetrofitClient
import com.example.taskvmg2.ui.service.TaskService

class TaskRepositoryOld() {

    private val tasks = mutableListOf<Task>()

    fun getTasks(): List<Task>  = tasks

    fun addTask(task: Task) = tasks.add(task)

    fun getTaskId(id: Int): Task? = tasks.find { it.id.equals(id) }

    fun removeTask(task: Task) = tasks.remove(task)

    fun toggleTask(task: Task) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(completed = !task.completed)
        }
    }

}