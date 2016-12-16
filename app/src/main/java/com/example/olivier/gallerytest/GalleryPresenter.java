package com.example.olivier.gallerytest;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.example.olivier.gallerytest.yelp.Business;
import com.example.olivier.gallerytest.yelp.SearchResponse;
import com.example.olivier.gallerytest.yelp.YelpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryPresenter implements Presenter<GalleryPresenter.View> {

  @Inject YelpClient mYelpClient;

  @Inject GalleryPresenter(Application application) {
    ((GalleryApplication) application.getApplicationContext()).getAppComponent().inject(this);
  }

  private View mView;

  public void queryYelp(String searchTerm) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("term", searchTerm);

    Call<SearchResponse> responseCall = mYelpClient.search("Mountain View", queryMap);
    responseCall.enqueue(new Callback<SearchResponse>() {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        if(mView != null) {
          mView.onDataSetChanged(response.body().businesses());
        }
      }

      @Override public void onFailure(Call<SearchResponse> call, Throwable t) {
        Log.e("Yelp OnFailure", t.toString());
      }
    });
  }

  @Override public void setView(View v) {
    mView = v;
  }

  public interface View {
    void onDataSetChanged(ArrayList<Business> data);
  }
}
