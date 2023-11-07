package com.appimage.core.di.viewmodel;

import androidx.lifecycle.ViewModel;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MultiViewModelFactory_Factory implements Factory<MultiViewModelFactory> {
  private final Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelFactoriesProvider;

  public MultiViewModelFactory_Factory(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelFactoriesProvider) {
    this.viewModelFactoriesProvider = viewModelFactoriesProvider;
  }

  @Override
  public MultiViewModelFactory get() {
    return newInstance(viewModelFactoriesProvider.get());
  }

  public static MultiViewModelFactory_Factory create(
      Provider<Map<Class<? extends ViewModel>, Provider<ViewModel>>> viewModelFactoriesProvider) {
    return new MultiViewModelFactory_Factory(viewModelFactoriesProvider);
  }

  public static MultiViewModelFactory newInstance(
      Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelFactories) {
    return new MultiViewModelFactory(viewModelFactories);
  }
}
