package io.github.umangjpatel.gallop.info;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.database.FirebaseQueryLiveData;

public class CourseInfoViewModel extends AndroidViewModel {

    private static DatabaseReference COURSE_CATALOG;

    private FirebaseQueryLiveData mLiveData;
    private MediatorLiveData<CourseInfo> mCourseInfoLiveData;

    public CourseInfoViewModel(@NonNull Application application) {
        super(application);
        mCourseInfoLiveData = new MediatorLiveData<>();
    }

    private void getCourseInfo() {
        mLiveData = new FirebaseQueryLiveData(COURSE_CATALOG);
        addLiveDataSource();
    }

    private void addLiveDataSource() {
        mCourseInfoLiveData.addSource(mLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(() -> {
                    CourseInfo courseInfo = dataSnapshot.getValue(CourseInfo.class);
                    mCourseInfoLiveData.postValue(courseInfo);
                }).start();
            } else
                mCourseInfoLiveData.setValue(null);
        });
    }

    public void setCourseCode(String courseCode) {
        COURSE_CATALOG = FirebaseDatabase.getInstance().getReference().child("courses").child(courseCode);
        getCourseInfo();
    }

    public MediatorLiveData<CourseInfo> getCourseInfoLiveData() {
        return mCourseInfoLiveData;
    }
}
