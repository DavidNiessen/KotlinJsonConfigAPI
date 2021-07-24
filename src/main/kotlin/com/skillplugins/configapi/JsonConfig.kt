package com.skillplugins.configapi

import com.google.gson.annotations.Expose

abstract class JsonConfig(@Transient val filePath: String)