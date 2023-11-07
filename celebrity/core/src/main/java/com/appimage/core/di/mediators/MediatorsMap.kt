package com.appimage.core.di.mediators

import javax.inject.Provider

typealias MediatorsMap = Map<Class<*>, @JvmSuppressWildcards Provider<Any>>

inline fun <reified T : Any> MediatorsMap.getMediator(): T {
    return getValue(T::class.java).get() as T
}