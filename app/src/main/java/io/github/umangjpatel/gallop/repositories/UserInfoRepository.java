package io.github.umangjpatel.gallop.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.github.umangjpatel.gallop.models.user.UserInfo;
import io.github.umangjpatel.gallop.models.user.UserInfoBuilder;

public class UserInfoRepository {

    private LiveData<UserInfo> mUserInfoLiveData;

    private DatabaseReference mDatabase;

    public UserInfoRepository() {
        mUserInfoLiveData = new MutableLiveData<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addUser(FirebaseUser currentUser) {
        UserInfo userInfo = getUserDetails(currentUser);
        mDatabase
                .child("users")
                .child(currentUser.getUid())
                .setValue(userInfo);

    }

    private UserInfo getUserDetails(FirebaseUser currentUser) {
        return new UserInfoBuilder()
                .setDisplayName(currentUser.getDisplayName())
                .setEmailAddress(currentUser.getEmail())
                .setPhoneNumber(currentUser.getPhoneNumber())
                .createUserInfo();
    }

    public LiveData<UserInfo> getUserInfoLiveData() {
        return mUserInfoLiveData;
    }
}
