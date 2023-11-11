package com.appimage.appimage.di.modules

import com.appimage.all_image_screen_api.modules.AllImageScreenMediatorModule
import com.appimage.like_image_screen_api.modules.LikeImageScreenMediatorModule
import com.appimage.mainscreen.di.modules.MainScreenMediatorModule
import dagger.Module

@Module(
    includes = [
        MainScreenMediatorModule::class,
        AllImageScreenMediatorModule::class,
        LikeImageScreenMediatorModule::class
    ]
)
interface MediatorsModule