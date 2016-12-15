package com.example.olivier.gallerytest.di.components;

import com.example.olivier.gallerytest.GalleryFragment;
import com.example.olivier.gallerytest.GalleryPresenter;
import com.example.olivier.gallerytest.di.modules.GalleryModule;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = { GalleryModule.class} )
public interface GalleryComponent {
  void inject(GalleryFragment fragment);
  void inject(GalleryPresenter presenter);
}
