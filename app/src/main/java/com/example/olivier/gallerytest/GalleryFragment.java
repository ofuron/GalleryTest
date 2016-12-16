package com.example.olivier.gallerytest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.olivier.gallerytest.yelp.Business;
import java.util.ArrayList;
import javax.inject.Inject;

public class GalleryFragment extends Fragment implements GalleryPresenter.View {

  private RecyclerView mRecyclerView;
  private GalleryAdapter mAdapter;
  private SearchView mSearchView;

  @Inject GalleryPresenter mPresenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.gallery_fragment, container, false);

    ((GalleryApplication) getActivity().getApplication()).getAppComponent().inject(this);

    mPresenter.setView(this);

    mAdapter = new GalleryAdapter(getActivity());
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mAdapter);

    mPresenter.queryYelp("pizza");

    return view;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.home_menu, menu);

    final MenuItem searchItem = menu.findItem(R.id.action_search);
    if(searchItem != null) {
      mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
      mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override public boolean onQueryTextSubmit(String query) {
          mPresenter.queryYelp(query);
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

  @Override public void onDataSetChanged(ArrayList<Business> data) {
    mAdapter.setData(data);
    mAdapter.notifyDataSetChanged();
  }
}
