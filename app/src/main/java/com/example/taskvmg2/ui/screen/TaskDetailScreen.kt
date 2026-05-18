package com.example.taskvmg2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg2.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: Int?,
    viewModel: TaskViewModel = viewModel()
) {

    LaunchedEffect(taskId) {

        viewModel.loadTask(taskId)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        if(taskId == null)
                            "Nueva Tarea"
                        else
                            "Detalle de Tarea"
                    )
                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()
                        }

                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.Top

        ) {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )

            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)

                ) {

                    Text(
                        text = "Información de la Tarea",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    // =========================
                    // ID
                    // =========================

                    OutlinedTextField(

                        value = viewModel.id,

                        onValueChange = {

                            viewModel.onIdChange(it)
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {

                            Text("ID")
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Tag,
                                contentDescription = null
                            )
                        },

                        singleLine = true
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // =========================
                    // TITLE
                    // =========================

                    OutlinedTextField(

                        value = viewModel.title,

                        onValueChange = {

                            viewModel.onTitleChange(it)
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {

                            Text("Título")
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    // =========================
                    // COMPLETED
                    // =========================

                    Card(

                        modifier = Modifier.fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.surfaceVariant
                        )

                    ) {

                        Row(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            verticalAlignment =
                                Alignment.CenterVertically,

                            horizontalArrangement =
                                Arrangement.SpaceBetween

                        ) {

                            Row(
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Text(
                                    text = "Completada"
                                )
                            }

                            Switch(

                                checked = viewModel.completed,

                                onCheckedChange = {

                                    viewModel.onCompletedChange(it)
                                }
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(32.dp)
                    )

                    // =========================
                    // BUTTONS
                    // =========================

                    Row(

                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(12.dp)

                    ) {

                        OutlinedButton(

                            modifier = Modifier.weight(1f),

                            onClick = {

                                navController.popBackStack()
                            }

                        ) {

                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text("Cancelar")
                        }

                        Button(

                            modifier = Modifier.weight(1f),

                            onClick = {

                                viewModel.saveTask()

                                navController.popBackStack()
                            }

                        ) {

                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text("Guardar")
                        }
                    }
                }
            }
        }
    }
}