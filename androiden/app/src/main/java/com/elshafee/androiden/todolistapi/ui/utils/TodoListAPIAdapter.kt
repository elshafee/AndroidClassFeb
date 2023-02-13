package com.elshafee.androiden.todolistapi.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androiden.databinding.ItemTodoBinding
import com.elshafee.androiden.todolistapi.model.TodoListApiData
import com.elshafee.androiden.todolistapi.services.TodoListApi

class TodoListAPIAdapter :RecyclerView.Adapter<TodoListAPIAdapter.TodoListApiViewHolder>(){

    inner class  TodoListApiViewHolder(val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object:DiffUtil.ItemCallback<TodoListApiData>(){
        override fun areItemsTheSame(oldItem: TodoListApiData, newItem: TodoListApiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TodoListApiData,
            newItem: TodoListApiData
        ): Boolean {
           return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,diffCallBack)

    var todos: List<TodoListApiData>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListApiViewHolder {
      return TodoListApiViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: TodoListApiViewHolder, position: Int) {
       holder.binding.apply {
           val singleTodo = todos[position]
           txTodoTitle.text = singleTodo.title
           chkTodoDone.isChecked = singleTodo.completed

       }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}