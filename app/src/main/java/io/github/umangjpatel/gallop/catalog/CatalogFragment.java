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

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.FragmentCatalogBinding;
import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.adapters.recyclerview.CatalogAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatalogFragment extends Fragment {

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
        mCatalogBinding.catalogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCatalogViewModel.getCourseCatalogLiveData().observe(this, dataSnapshot -> {
            if (dataSnapshot != null) {
                List<CourseInfo> courseInfoList = new ArrayList<>();
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    CourseInfo courseInfo = courseSnapshot.getValue(CourseInfo.class);
                    courseInfoList.add(courseInfo);
                }
                if (mCatalogAdapter == null) {
                    mCatalogAdapter = new CatalogAdapter(courseInfoList);
                    mCatalogBinding.catalogRecyclerView.setAdapter(mCatalogAdapter);
                } else {
                    mCatalogAdapter.setCourseInfoList(courseInfoList);
                    mCatalogAdapter.notifyDataSetChanged();
                }
            }
        });
        return mCatalogBinding.getRoot();
    }
}
