package com.test.repository

import com.test.models.TodoModel
import java.sql.Connection
import java.sql.DriverManager

class TodoImp : TodoRepo {
    object DatabaseConnection {
        private const val URL = "jdbc:mysql://localhost:3306/ktor_db"
        private const val USER = "root"
        private const val PASSWORD = "Hussnain49*"
        const val TBL_NAME = "todotbl"

        fun getConnection(): Connection {
            return DriverManager.getConnection(URL, USER, PASSWORD)
        }
    }

    private var connection: Connection? = null

    init {
        connection = DatabaseConnection.getConnection()
    }


    override suspend fun getTodoList(): List<TodoModel> {
        val todos = mutableListOf<TodoModel>()
        val query = "SELECT * FROM ${DatabaseConnection.TBL_NAME}"
        connection?.prepareStatement(query).use { statement ->
            val resultSet = statement?.executeQuery()
            resultSet?.use { set ->
                while (set.next()) {
                    todos.add(TodoModel(set.getInt("todoId"), set.getString("todoTitle"), set.getString("dateTime")))
                }
            }
        }
        return todos
    }

    override suspend fun getTodoById(id: Int): TodoModel? {
        return null
    }

}