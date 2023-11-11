package com.appimage.core.di.providers

interface ApplicationProvider :
    AndroidDependenciesProvider,
    NetworkProvider,
    MediatorsProvider,
    MainActivityContainerProvider,
    StorageProvider,
    MainContentFragmentContainerProvider