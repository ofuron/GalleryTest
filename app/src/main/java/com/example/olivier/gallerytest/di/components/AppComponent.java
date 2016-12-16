package com.example.olivier.gallerytest.di.components;

import com.example.olivier.gallerytest.GalleryFragment;
import com.example.olivier.gallerytest.GalleryPresenter;
import com.example.olivier.gallerytest.di.modules.AppModule;
import com.example.olivier.gallerytest.di.modules.GalleryModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class, GalleryModule.class } )
public interface AppComponent {
  void inject(GalleryFragment fragment);
  void inject(GalleryPresenter presenter);
}
