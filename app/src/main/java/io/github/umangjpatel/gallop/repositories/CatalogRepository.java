package io.github.umangjpatel.gallop.repositories;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.gallop.models.course.CourseInfo;

public class CatalogRepository {

    private static CatalogRepository sCatalogRepository = new CatalogRepository();
    private DatabaseReference mDatabase;
    private List<CourseInfo> mCourseInfoList;

    private CatalogRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mCourseInfoList = new ArrayList<>();
        retriveCatalogCoursesFromDatabase();
    }

    public static CatalogRepository getInstance() {
        return sCatalogRepository;
    }

    private void retriveCatalogCoursesFromDatabase() {
        mDatabase
                .child("courses")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<CourseInfo> courseInfoList = new ArrayList<>();
                        for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                            CourseInfo courseInfo = courseSnapshot.getValue(CourseInfo.class);
                            courseInfoList.add(courseInfo);
                        }
                        mCourseInfoList = courseInfoList;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public List<CourseInfo> getCourseInfoList() {
        return mCourseInfoList;
    }
}
