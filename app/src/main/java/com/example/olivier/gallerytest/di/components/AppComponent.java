package com.example.olivier.gallerytest.di.components;

import com.example.olivier.gallerytest.GalleryActivity;
import com.example.olivier.gallerytest.GalleryFragment;
import com.example.olivier.gallerytest.di.modules.AppModule;
import com.example.olivier.gallerytest.di.modules.YelpNetModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class, YelpNetModule.class } )
public interface AppComponent {
  void inject(GalleryFragment fragment);
}
