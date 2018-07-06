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
        return mCatalogBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdapter();
    }

    public void updateAdapter() {
        if (mCatalogAdapter == null) {
            mCatalogAdapter = new CatalogAdapter();
            mCatalogBinding.catalogRecyclerView.setAdapter(mCatalogAdapter);
        } else
            mCatalogAdapter.notifyDataSetChanged();
    }

}
