package io.github.umangjpatel.gallop.models.user;

public class UserInfo {

    private String mDisplayName, mEmailAddress, mPhoneNumber;

    public UserInfo() {
        //Empty constructor required
    }

    UserInfo(String displayName, String emailAddress, String phoneNumber) {
        mDisplayName = displayName;
        mEmailAddress = emailAddress;
        mPhoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }
}
