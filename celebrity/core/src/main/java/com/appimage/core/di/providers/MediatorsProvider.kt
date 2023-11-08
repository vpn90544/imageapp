package com.appimage.core.di.providers

import com.appimage.core.di.mediators.MediatorsMap
import com.appimage.core.di.qualifiers.MediatorsQualifier

interface MediatorsProvider {

    @MediatorsQualifier
    fun provideMediatorsMap(): MediatorsMap
}