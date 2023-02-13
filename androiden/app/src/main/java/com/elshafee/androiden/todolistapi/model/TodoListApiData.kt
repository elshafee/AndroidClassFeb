package com.elshafee.androiden.todolistapi.model

import com.elshafee.androiden.todolistapi.services.TodoListApi

data class TodoListApiData(
    val completed: Boolean,
    val title: String,
    val id: Int,
    val userId: Int,
)