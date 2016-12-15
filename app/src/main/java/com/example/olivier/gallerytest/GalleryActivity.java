package com.example.olivier.gallerytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gallery);

    ((GalleryApplication) getApplication()).createGalleryComponent();

    GalleryFragment fragment = new GalleryFragment();

    getFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit();
  }
}
