package com.skillplugins.configapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

abstract class JsonConfigLoader<T : JsonConfig>(private val jsonConfig: T) {

    private val gson = Gson()
    private val file = File(jsonConfig.filePath)
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
        if (file.exists()) {
            config = gson.fromJson(JsonReader(FileReader(file)), jsonConfig.javaClass)
        } else {
            file.parentFile?.mkdir()
            save(jsonConfig)
        }
    }

    fun save(jsonConfig: T) {
        if (jsonConfig == null
            || (file.exists()
                    && !file.delete())
        ) return

        val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
        val fileWriter = FileWriter(file)

        fileWriter.write(gson.toJson(jsonConfig))
        fileWriter.flush()
        fileWriter.close()

        config = jsonConfig
    }

}
