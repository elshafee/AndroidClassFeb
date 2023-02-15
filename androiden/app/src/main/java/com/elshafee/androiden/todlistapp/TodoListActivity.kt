package com.elshafee.androiden.todlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androiden.databinding.ActivityTodoListBinding

class TodoListActivity : Fragment() {
    lateinit var binding: ActivityTodoListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        val view = binding.root
        val todoListItem = mutableListOf(
            Todos("doing android session", false),
            Todos("doing android session", false),
            Todos("doing android session", false),
            Todos("doing android session", false),
            Todos("doing android session", false),
            Todos("doing android session", false),

            )
        val adapter = TodoAdapter(todoListItem)
        binding.rvTodos.adapter = adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(activity)

        binding.btnAddTodo.setOnClickListener {
            val newTodoTitle = binding.etAddTodo.text.toString()
            val newTodo = Todos(newTodoTitle, false)

            todoListItem.add(newTodo)
            adapter.notifyDataSetChanged()
            binding.etAddTodo.text.clear()
        }
        return view
    }


}