package com.example.olivier.gallerytest.yelp;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface YelpClient
{
  @GET("/v2/search")
  Call<SearchResponse> search(@Query("location") String location, @QueryMap
  Map<String, String> params);
}
