package com.example.olivier.gallerytest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.olivier.gallerytest.yelp.SearchResponse;
import com.example.olivier.gallerytest.yelp.YelpClient;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

  private RecyclerView mRecyclerView;
  private GalleryAdapter mAdapter;
  private SearchView mSearchView;

  @Inject YelpClient mYelpClient;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.gallery_fragment, container, false);

    ((GalleryApplication) getActivity().getApplication()).getAppComponent().inject(this);

    mAdapter = new GalleryAdapter(getActivity());
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mAdapter);

    queryYelp("pizza");

    return view;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.home_menu, menu);

    final MenuItem searchItem = menu.findItem(R.id.action_search);
    if(searchItem != null) {
      mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
      mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override public boolean onQueryTextSubmit(String query) {
          queryYelp(query);
          return true;
        }

        @Override public boolean onQueryTextChange(String newText) {
          return false;
        }
      });
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  private void queryYelp(String searchTerm) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put("term", searchTerm);

    Call<SearchResponse> responseCall = mYelpClient.search("Mountain View", queryMap);
    responseCall.enqueue(new Callback<SearchResponse>() {
      @Override
      public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
        mAdapter.setData(response.body().businesses());
        mAdapter.notifyDataSetChanged();
      }

      @Override public void onFailure(Call<SearchResponse> call, Throwable t) {
        Log.e("Yelp OnFailure", t.toString());
      }
    });
  }
}
