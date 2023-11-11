package com.appimage.appimage.di.modules

import com.appimage.all_image_screen_api.mediators.AllImageScreenMediator
import com.appimage.all_image_screen_api.modules.AllImageScreenModule
import com.appimage.allimage.di.modules.AllImageScreenMediatorModule
import com.appimage.like_image_screen_api.modules.LikeImageScreenModule
import com.appimage.likeimage.di.modules.LikeScreenMediatorModule
import com.appimage.mainscreen.di.modules.MainScreenMediatorModule
import dagger.Module

@Module(
    includes = [
        MainScreenMediatorModule::class,
        LikeScreenMediatorModule::class,
        AllImageScreenMediatorModule::class
    ]
)
interface MediatorsModule