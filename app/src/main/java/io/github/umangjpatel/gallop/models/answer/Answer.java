package io.github.umangjpatel.gallop.models.answer;

public class Answer {

    private String mKey, mAnswer, mAuthor;
    private long mDate;

    public Answer() {
        //Left intentionally for Firebase services
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String question) {
        mAnswer = question;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(long date) {
        mDate = date;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

}
