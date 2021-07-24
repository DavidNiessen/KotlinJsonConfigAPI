package com.skillplugins.configapi.example

fun main() {
    val config: ExampleConfig? = ExampleConfigLoader.config

    val version: String? = config?.version
    val messages: List<String>? = config?.messages

    println(version)
    messages?.forEach { println(it) }
}