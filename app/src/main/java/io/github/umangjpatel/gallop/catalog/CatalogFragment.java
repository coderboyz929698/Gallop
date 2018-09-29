package io.github.umangjpatel.gallop.catalog;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.FragmentCatalogBinding;
import io.github.umangjpatel.gallop.utils.adapters.recyclerview.CatalogAdapter;

import static android.support.v7.widget.SearchView.OnQueryTextListener;

public class CatalogFragment extends Fragment {

    private static final int EMPTY_COURSES = 0, LOADING_COURSES = 1, LOADED_COURSES = 2;

    private FragmentCatalogBinding mCatalogBinding;
    private CatalogViewModel mCatalogViewModel;
    private CatalogAdapter mCatalogAdapter;

    public CatalogFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static CatalogFragment newInstance() {
        return new CatalogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCatalogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalog, container, false);
        mCatalogViewModel = ViewModelProviders.of(this).get(CatalogViewModel.class);
        updateUI(LOADING_COURSES);
        mCatalogViewModel.getCourseInfoLiveData().observe(this, courseInfoList -> {
            if (courseInfoList != null && courseInfoList.size() > 0) {
                updateUI(LOADED_COURSES);
                if (mCatalogAdapter == null) {
                    mCatalogAdapter = new CatalogAdapter(courseInfoList);
                    mCatalogBinding.catalogRecyclerView.setAdapter(mCatalogAdapter);
                } else {
                    mCatalogAdapter.setCourseInfoList(courseInfoList);
                    mCatalogAdapter.notifyDataSetChanged();
                }
            } else
                updateUI(EMPTY_COURSES);
        });
        mCatalogBinding.catalogSearchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mCatalogViewModel.searchCourse(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCatalogViewModel.searchCourse(newText);
                return false;
            }
        });
        mCatalogBinding.catalogSearchView.setOnCloseListener(() -> {
            mCatalogViewModel.getCatalog();
            return true;
        });
        return mCatalogBinding.getRoot();
    }

    private void updateUI(int layoutType) {
        switch (layoutType) {
            case LOADING_COURSES:
                mCatalogBinding.emptyCourses.setVisibility(View.GONE);
                mCatalogBinding.progressBar.setVisibility(View.VISIBLE);
                mCatalogBinding.catalogRecyclerView.setVisibility(View.GONE);
                mCatalogBinding.catalogRecyclerView.setLayoutManager(null);
                break;
            case LOADED_COURSES:
                mCatalogBinding.emptyCourses.setVisibility(View.GONE);
                mCatalogBinding.progressBar.setVisibility(View.GONE);
                mCatalogBinding.catalogRecyclerView.setVisibility(View.VISIBLE);
                mCatalogBinding.catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case EMPTY_COURSES:
                mCatalogBinding.emptyCourses.setVisibility(View.VISIBLE);
                mCatalogBinding.progressBar.setVisibility(View.GONE);
                mCatalogBinding.catalogRecyclerView.setVisibility(View.GONE);
                mCatalogBinding.catalogRecyclerView.setLayoutManager(null);
                break;
        }

    }
}
