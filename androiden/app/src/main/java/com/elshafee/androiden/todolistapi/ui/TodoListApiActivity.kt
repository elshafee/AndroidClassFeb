package com.elshafee.androiden.todolistapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityTodoListApiBinding
import com.elshafee.androiden.todolistapi.services.RetrofitInstanceTodoListApi
import com.elshafee.androiden.todolistapi.ui.utils.TodoListAPIAdapter
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TodoListApiActivity : AppCompatActivity() {
    lateinit var todolistAdapter:TodoListAPIAdapter
    lateinit var binding:ActivityTodoListApiBinding
    private val TAG ="TodoListApiActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyerView()
        lifecycleScope.launchWhenCreated {
            binding.progressBarTodoListApi.isVisible = true
            val response = try {
                RetrofitInstanceTodoListApi.api.getTodos()
            }catch (e:IOException){
                Log.d(TAG,"You don't have internet connection")
                binding.progressBarTodoListApi.isVisible = false
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.d(TAG,"Unecxpected Response")
                binding.progressBarTodoListApi.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                todolistAdapter.todos = response.body()!!
            }else{
                Log.d(TAG,"Response is not Successful")
            }
            binding.progressBarTodoListApi.isVisible = false
        }




    }

    private fun setupRecyerView() = binding.rvTodoListApi.apply {
        todolistAdapter = TodoListAPIAdapter()
        adapter = todolistAdapter
        layoutManager = LinearLayoutManager(this@TodoListApiActivity)
    }
}