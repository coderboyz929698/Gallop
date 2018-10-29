package io.github.umangjpatel.gallop.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivityCourseInfoBinding;

public class CourseInfoActivity extends AppCompatActivity {

    private static final String EXTRA_COURSE_CODE =
            CourseInfoActivity.class.getSimpleName() + ".extra_course_code_info",
            KEY_COURSE_CODE = "KEY_COURSE_CODE_INFO";
    private ActivityCourseInfoBinding mCourseInfoBinding;
    private String mCourseCode;

    private CourseInfoViewModel mCourseInfoViewModel;

    public static Intent newIntent(Context packageContext, String courseCode) {
        Intent intent = new Intent(packageContext, CourseInfoActivity.class);
        intent.putExtra(EXTRA_COURSE_CODE, courseCode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCourseInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_course_info);
        mCourseInfoBinding.setLifecycleOwner(this);
        setSupportActionBar(mCourseInfoBinding.courseInfoToolbar);
        mCourseInfoViewModel = ViewModelProviders.of(this).get(CourseInfoViewModel.class);
        mCourseCode = savedInstanceState != null ? savedInstanceState.getString(KEY_COURSE_CODE) : getIntent().getStringExtra(EXTRA_COURSE_CODE);
        mCourseInfoViewModel.setCourseCode(mCourseCode);
        updateCourseInfo();
    }

    private void updateCourseInfo() {
        mCourseInfoViewModel.getCourseInfoLiveData().observe(this, courseInfo -> {
            if (courseInfo != null) {
                mCourseInfoBinding.courseInfoToolbar.setTitle(courseInfo.getCourseCode());
                mCourseInfoBinding.courseInfoToolbar.setSubtitle(courseInfo.getTitle());
                mCourseInfoBinding.courseSummaryTextView.setText(courseInfo.getSummary());
                mCourseInfoBinding.coursePrerequisitesTextView.setText(courseInfo.getPrerequisites());
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_COURSE_CODE, mCourseCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
