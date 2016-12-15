package com.example.olivier.gallerytest;

import android.app.Application;
import com.example.olivier.gallerytest.di.components.AppComponent;
import com.example.olivier.gallerytest.di.components.DaggerAppComponent;
import com.example.olivier.gallerytest.di.components.GalleryComponent;
import com.example.olivier.gallerytest.di.modules.AppModule;
import com.example.olivier.gallerytest.di.modules.GalleryModule;

public class GalleryApplication extends Application {

  private AppComponent mAppComponent;
  private GalleryComponent mGalleryComponent;

  @Override public void onCreate() {
    super.onCreate();

    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  public GalleryComponent getGalleryComponent() {
    return mGalleryComponent;
  }

  public GalleryComponent createGalleryComponent() {
    if( mAppComponent != null) {
      mGalleryComponent = getAppComponent().plus(new GalleryModule("https://api.yelp.com"));
    }
    return mGalleryComponent;
  }
}
