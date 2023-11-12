package com.appimage.network.di.modules.exceptions

import okhttp3.ResponseBody

open class HttpExceptionWithBody(val responseBody: ResponseBody?) : RuntimeException()