package com.example.olivier.gallerytest;

import android.app.Application;
import com.example.olivier.gallerytest.di.components.AppComponent;
import com.example.olivier.gallerytest.di.components.DaggerAppComponent;
import com.example.olivier.gallerytest.di.modules.AppModule;
import com.example.olivier.gallerytest.di.modules.GalleryModule;

public class GalleryApplication extends Application {

  private AppComponent mAppComponent;

  @Override public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .galleryModule(new GalleryModule("https://api.yelp.com"))
        .build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }
}
