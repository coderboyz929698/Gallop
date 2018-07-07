package io.github.umangjpatel.gallop.models.course;

public class CourseInfoBuilder {

    private String mCourseCode, mTitle, mSummary, mPrerequisites;

    public CourseInfoBuilder setCourseCode(String courseCode) {
        mCourseCode = courseCode;
        return this;
    }

    public CourseInfoBuilder setTitle(String title) {
        mTitle = title;
        return this;
    }

    public CourseInfoBuilder setSummary(String summary) {
        mSummary = summary;
        return this;
    }

    public CourseInfoBuilder setPrerequisites(String prerequisites) {
        mPrerequisites = prerequisites;
        return this;
    }

    public CourseInfo createCourseInfo() {
        return new CourseInfo(mCourseCode, mTitle, mSummary, mPrerequisites);
    }

}
