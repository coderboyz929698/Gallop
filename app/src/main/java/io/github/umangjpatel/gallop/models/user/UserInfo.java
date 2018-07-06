package io.github.umangjpatel.gallop.models.user;

public class UserInfo {

    private String mDisplayName, mEmailAddress;

    public UserInfo() {
        //Empty constructor required
    }

    UserInfo(String displayName, String emailAddress) {
        mDisplayName = displayName;
        mEmailAddress = emailAddress;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }
}
