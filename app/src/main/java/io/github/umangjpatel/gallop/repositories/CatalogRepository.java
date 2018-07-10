package io.github.umangjpatel.gallop.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.gallop.models.course.CourseInfo;
import io.github.umangjpatel.gallop.utils.adapters.recyclerview.CatalogAdapter;

public class CatalogRepository {

    private static CatalogRepository sCatalogRepository;
    private DatabaseReference mDatabase;
    private MutableLiveData<List<CourseInfo>> mCourseInfoLiveData;

    public static CatalogRepository getInstance() {
        if (sCatalogRepository == null) {
            synchronized (CatalogAdapter.class) {
                if (sCatalogRepository == null)
                    sCatalogRepository = new CatalogRepository();
            }
        }
        return sCatalogRepository;
    }

    private CatalogRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private List<CourseInfo> retrieveCatalogCoursesFromDatabase() {
        List<CourseInfo> courseInfoList = new ArrayList<>();
        mDatabase
                .child("courses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                            CourseInfo courseInfo = courseSnapshot.getValue(CourseInfo.class);
                            courseInfoList.add(courseInfo);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        return courseInfoList;
    }

    public MutableLiveData<List<CourseInfo>> getCourseInfoList() {
        if (mCourseInfoLiveData == null) {
            mCourseInfoLiveData = new MutableLiveData<>();
            mCourseInfoLiveData.setValue(retrieveCatalogCoursesFromDatabase());
        }
        return mCourseInfoLiveData;
    }
}
