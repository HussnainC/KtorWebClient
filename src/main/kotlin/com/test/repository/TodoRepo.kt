package com.test.repository

import com.test.models.TodoModel

interface TodoRepo {
    suspend fun getTodoList(): List<TodoModel>
    suspend fun getTodoById(id:Int): TodoModel?
}