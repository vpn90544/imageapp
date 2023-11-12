package com.appimage.network.di.modules.calladapters

import com.appimage.network.di.modules.exceptions.BadRequestException
import okhttp3.ResponseBody
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import com.appimage.network.di.modules.exceptions.ForbiddenException
import com.appimage.network.di.modules.exceptions.HttpExceptionWithBody
import com.appimage.network.di.modules.exceptions.InternalServerException
import com.appimage.network.di.modules.exceptions.NotAcceptableException
import com.appimage.network.di.modules.exceptions.NotFoundException
import com.appimage.network.di.modules.exceptions.ServerConflictException
import com.appimage.network.di.modules.exceptions.UnexpectedHttpException
import java.net.HttpURLConnection

internal fun <T : Any> mapResponseToResult(response: Response<T>): Result<T> {
    val body = response.body()
    return if (response.isSuccessful && body != null) {
        Result.success(body)
    } else {
        Result.failure(mapCodeToException(response.code(), response.errorBody()))
    }
}

internal fun mapExceptionToNetworkException(throwable: Throwable): Exception {
    return when (throwable) {
        is IOException -> RuntimeException()
        is HttpException -> mapCodeToException(throwable.code())
        else -> RuntimeException()
    }
}

internal fun mapCodeToException(httpCode: Int, errorBody: ResponseBody? = null): HttpExceptionWithBody {
    return when (httpCode) {
        HttpURLConnection.HTTP_CONFLICT -> {
            ServerConflictException(errorBody)
        }
        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
            InternalServerException(errorBody)
        }
        HttpURLConnection.HTTP_BAD_REQUEST -> {
            BadRequestException(errorBody)
        }
        HttpURLConnection.HTTP_NOT_FOUND -> {
            NotFoundException(errorBody)
        }
        HttpURLConnection.HTTP_NOT_ACCEPTABLE -> {
            NotAcceptableException(errorBody)
        }
        HttpURLConnection.HTTP_FORBIDDEN -> {
            ForbiddenException(errorBody)
        }
        else -> {
            UnexpectedHttpException(errorBody)
        }
    }
}