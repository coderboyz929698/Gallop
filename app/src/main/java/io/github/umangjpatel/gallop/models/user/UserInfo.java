package io.github.umangjpatel.gallop.models.user;

public class UserInfo {

    private String mDisplayName, mEmailAddress, mPhotoURL;

    public UserInfo() {
        //Empty constructor required
    }

    UserInfo(String displayName, String emailAddress, String photoURL) {
        mDisplayName = displayName;
        mEmailAddress = emailAddress;
        mPhotoURL = photoURL;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        mPhotoURL = photoURL;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }
}
