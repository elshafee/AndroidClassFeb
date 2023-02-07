package com.elshafee.androiden.todlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityTodoListBinding

class TodoListActivity : AppCompatActivity() {
    lateinit var binding:ActivityTodoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val todoListItem = mutableListOf(
            Todos("doing android session",false),
            Todos("doing android session",false),
            Todos("doing android session",false),
            Todos("doing android session",false),
            Todos("doing android session",false),
            Todos("doing android session",false),

        )
        val adapter = TodoAdapter(todoListItem)
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val newTodoTitle = binding.etAddTodo.text.toString()
            val newTodo = Todos(newTodoTitle,false)

            todoListItem.add(newTodo)
            adapter.notifyDataSetChanged()
            binding.etAddTodo.text.clear()
        }
    }
}