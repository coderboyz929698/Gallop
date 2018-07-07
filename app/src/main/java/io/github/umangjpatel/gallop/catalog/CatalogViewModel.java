package io.github.umangjpatel.gallop.catalog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.repositories.CatalogRepository;

public class CatalogViewModel extends AndroidViewModel {

    private CatalogRepository mCatalogRepository;
    private MutableLiveData<List<CourseInfo>> mCourseCatalogLiveData;

    public CatalogViewModel(@NonNull Application application) {
        super(application);
        mCatalogRepository = CatalogRepository.getInstance();
        mCourseCatalogLiveData = new MutableLiveData<>();
        mCourseCatalogLiveData.setValue(mCatalogRepository.getCourseInfoList());
    }

    public MutableLiveData<List<CourseInfo>> getCourseCatalogLiveData() {
        return mCourseCatalogLiveData;
    }
}
