package com.example.taskvmg2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg2.ui.screen.TaskDetailScreen
import com.example.taskvmg2.ui.screen.TaskListScreen
import com.example.taskvmg2.ui.viewmodel.TaskListViewModel
import com.example.taskvmg2.ui.viewmodel.TaskListViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskvmg2.ui.service.ServiceLocator

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()

    NavHost(navController = navController
        , startDestination = TaskList)
    {
        composable<TaskList>
        {
            val listViewModel : TaskListViewModel =
                viewModel(factory =
                    TaskListViewModelFactory(
                        ServiceLocator.taskRepository
                    )
                )

            TaskListScreen(navController = navController,listViewModel)
        }
        composable<TaskDetail>{ backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(navController = navController,
                taskId = route.taskId)
        }

    }
}