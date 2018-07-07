package io.github.umangjpatel.gallop.models.course;

public class CourseInfo {

    private String mCourseCode, mTitle, mSummary, mPrerequisites;

    public CourseInfo() {
        //Left intentionally for firebase
    }

    CourseInfo(String courseCode, String title, String summary, String prerequisites) {
        mCourseCode = courseCode;
        mTitle = title;
        mSummary = summary;
        mPrerequisites = prerequisites;
    }

    public String getCourseCode() {
        return mCourseCode;
    }

    public void setCourseCode(String courseCode) {
        mCourseCode = courseCode;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getPrerequisites() {
        return mPrerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        mPrerequisites = prerequisites;
    }
}
