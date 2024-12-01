package com.test

import com.test.repository.TodoImp
import com.test.repository.TodoRepo
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args)
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        module()
//    }.start(wait = true)
}

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
    configureKtor()
    configureResponses()
}

private val appModule = module {
    single<TodoRepo> { TodoImp() }
}