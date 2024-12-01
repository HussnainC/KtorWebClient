package com.test

import com.test.repository.TodoRepo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Application.configureResponses() {
    val repo by inject<TodoRepo>()
    routing {
        get("/todoList") {
            val dataList = bgWorker {
                repo.getTodoList()
            }
            dataList ?: return@get call.respondText("Data no found", status = HttpStatusCode.NotFound)
            call.respond(dataList)
        }
        get("/todoList/{id}") {
            call.parameters["id"]?.let { id ->
                val data = bgWorker {
                    repo.getTodoById(id.toInt())
                }
                data ?: return@get call.respondText("Data no found", status = HttpStatusCode.NotFound)
                call.respond(data)

            } ?: call.respondText("Data not found", status = HttpStatusCode.NotFound)

        }
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

private suspend fun <T> bgWorker(work: suspend () -> T): T? {
    return withContext(Dispatchers.IO) {
        work.invoke()
    }
}
