package io.github.umangjpatel.gallop.catalog;

import android.app.Application;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class CatalogViewModel extends AndroidViewModel {

    private static final DatabaseReference COURSE_CATALOG =
            FirebaseDatabase.getInstance().getReference().child("courses");

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<List<CourseInfo>> mCourseInfoLiveData;

    public CatalogViewModel(@NonNull Application application) {
        super(application);
        mCourseInfoLiveData = new MediatorLiveData<>();
        getCatalog();
    }

    @NonNull
    public LiveData<List<CourseInfo>> getCourseInfoLiveData() {
        return mCourseInfoLiveData;
    }

    public void getCatalog() {
        mLiveData = new FirebaseQueryLiveData(COURSE_CATALOG);
        addLiveDataSource();
    }

    private void addLiveDataSource() {
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

    public void searchCourse(String query) {
        mLiveData = new FirebaseQueryLiveData(generateQuery(query));
        addLiveDataSource();
    }

    private Query generateQuery(String query) {
        return FirebaseDatabase
                .getInstance()
                .getReference()
                .child("courses")
                .orderByChild("courseCode")
                .startAt(query)
                .endAt(query + "\uf8ff");
    }
}
