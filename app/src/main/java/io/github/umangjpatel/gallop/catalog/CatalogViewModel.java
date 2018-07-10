package io.github.umangjpatel.gallop.catalog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class CatalogViewModel extends AndroidViewModel {

    private static final DatabaseReference COURSE_CATALOG =
            FirebaseDatabase.getInstance().getReference().child("courses");
    private FirebaseQueryLiveData mCourseCatalogLiveData;

    public CatalogViewModel(@NonNull Application application) {
        super(application);
        mCourseCatalogLiveData = new FirebaseQueryLiveData(COURSE_CATALOG);
    }

    public LiveData<DataSnapshot> getCourseCatalogLiveData() {
        return mCourseCatalogLiveData;
    }
}
