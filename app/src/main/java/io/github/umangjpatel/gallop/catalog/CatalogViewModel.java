package io.github.umangjpatel.gallop.catalog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class CatalogViewModel extends AndroidViewModel {

    private static final DatabaseReference COURSE_CATALOG =
            FirebaseDatabase.getInstance().getReference().child("courses");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<CourseInfo>> mCourseInfoLiveData;

    public CatalogViewModel(@NonNull Application application) {
        super(application);
        mLiveData = new FirebaseQueryLiveData(COURSE_CATALOG);
        mCourseInfoLiveData = new MediatorLiveData<>();
        mCourseInfoLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    List<CourseInfo> courseInfoList = new ArrayList<>();
                    for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                        CourseInfo courseInfo = courseSnapshot.getValue(CourseInfo.class);
                        courseInfoList.add(courseInfo);
                    }
                    mCourseInfoLiveData.postValue(courseInfoList);
                }).start();
            } else
                mCourseInfoLiveData.setValue(null);
        });
    }

    @NonNull
    public LiveData<List<CourseInfo>> getCourseInfoLiveData() {
        return mCourseInfoLiveData;
    }
}
