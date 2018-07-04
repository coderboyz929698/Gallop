package io.github.umangjpatel.gallop.models.user;

public class UserInfoBuilder {

    private String mDisplayName, mEmailAddress, mPhoneNumber;

    public UserInfoBuilder() {

    }

    public UserInfoBuilder setDisplayName(String displayName) {
        mDisplayName = displayName;
        return this;
    }

    public UserInfoBuilder setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
        return this;
    }

    public UserInfoBuilder setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
        return this;
    }

    public UserInfo createUserInfo() {
        return new UserInfo(mDisplayName, mEmailAddress, mPhoneNumber);
    }

}
