package com.test

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureKtor() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }


}
