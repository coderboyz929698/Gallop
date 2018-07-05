package io.github.umangjpatel.gallop.models.user;

public class UserInfoBuilder {

    private String mDisplayName, mEmailAddress, mPhotoURL;

    private void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public UserInfoBuilder setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
        setDisplayName(mEmailAddress.split("@")[0]);
        return this;
    }

    public UserInfoBuilder setPhotoURL(String photoURL) {
        mPhotoURL = photoURL;
        return this;
    }

    public UserInfo createUserInfo() {
        return new UserInfo(mDisplayName, mEmailAddress, mPhotoURL);
    }

}
