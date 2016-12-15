package com.example.olivier.gallerytest.di.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
  private final Application mApplication;

  public AppModule(final Application application) {
    mApplication = application;
  }

  @Provides
  Application getApplication() {
    return mApplication;
  }
}
