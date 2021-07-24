package com.skillplugins.configapi.example

import com.skillplugins.configapi.JsonConfig

data class ExampleConfig(
    val version: String = "1.0",
    val messages: List<String> = listOf(
        "Message1",
        "Message2"
    ),
) : JsonConfig("configs/config.json")