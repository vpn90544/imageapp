package com.appimage.network.di.modules.exceptions

import com.appimage.network.di.modules.exceptions.HttpExceptionWithBody
import okhttp3.ResponseBody

class UnexpectedHttpException(responseBody: ResponseBody? = null) : HttpExceptionWithBody(responseBody)