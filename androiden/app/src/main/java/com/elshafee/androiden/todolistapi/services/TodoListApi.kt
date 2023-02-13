package com.elshafee.androiden.todolistapi.services

import com.elshafee.androiden.todolistapi.model.TodoListApiData
import retrofit2.Response
import retrofit2.http.GET

interface TodoListApi {

    @GET("/todos")
    suspend fun getTodos():Response<List<TodoListApiData>>
}