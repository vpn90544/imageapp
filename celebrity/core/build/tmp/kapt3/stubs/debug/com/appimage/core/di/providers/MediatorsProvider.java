package com.appimage.core.di.providers;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a!\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\u0002\b\u00060\u0003j\u0002`\u0007H\'\u00a8\u0006\b"}, d2 = {"Lcom/appimage/core/di/providers/MediatorsProvider;", "", "provideMediatorsMap", "", "Ljava/lang/Class;", "Ljavax/inject/Provider;", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/appimage/core/di/mediators/MediatorsMap;", "core_debug"})
public abstract interface MediatorsProvider {
    
    @com.appimage.core.di.qualifiers.MediatorsQualifier
    @org.jetbrains.annotations.NotNull
    public abstract java.util.Map<java.lang.Class<?>, javax.inject.Provider<java.lang.Object>> provideMediatorsMap();
}