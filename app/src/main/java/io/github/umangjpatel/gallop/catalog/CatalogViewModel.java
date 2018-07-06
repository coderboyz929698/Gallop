package io.github.umangjpatel.gallop.catalog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.github.umangjpatel.gallop.repositories.CatalogRepository;

public class CatalogViewModel extends AndroidViewModel {

    private CatalogRepository mCatalogRepository;

    public CatalogViewModel(@NonNull Application application) {
        super(application);
        mCatalogRepository = CatalogRepository.getInstance();
    }
}
