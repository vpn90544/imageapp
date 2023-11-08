package com.appimage.appimage.di.modules

import com.appimage.mainscreen.presentation.di.modules.MainScreenMediatorModule
import dagger.Module

@Module(
    includes = [
        MainScreenMediatorModule::class
    ]
)
interface MediatorsModule