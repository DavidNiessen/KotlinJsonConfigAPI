package com.skillplugins.configapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

abstract class JsonConfigLoader<T : JsonConfig>(private val jsonConfig: T) {

    private val gson = Gson()
    private var loaded = false

    var config: T? = null
        get() {
            if (!loaded) {
                loadConfig()
                loaded = true
            }
            return field
        }

    private fun loadConfig() {
        val file = File(jsonConfig.filePath)

        if (file.exists()) {
            config = gson.fromJson(JsonReader(FileReader(file)), jsonConfig.javaClass)
        } else {
            file.parentFile?.mkdir()

            val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
            val fileWriter = FileWriter(file)

            fileWriter.write(gson.toJson(jsonConfig))
            fileWriter.flush()
            fileWriter.close()

            config = jsonConfig
        }
    }

}