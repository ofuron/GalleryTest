package com.example.olivier.gallerytest;

public class GalleryItemModel {
  private String mTitle;
  private String mUrl;

  GalleryItemModel() {}

  GalleryItemModel(String title, String url) {
    mTitle = title;
    mUrl = url;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public String getUrl() {
    return mUrl;
  }

  public void setUrl(String url) {
    this.mUrl = url;
  }
}
