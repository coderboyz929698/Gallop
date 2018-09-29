package io.github.umangjpatel.gallop.repositories;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import io.github.umangjpatel.gallop.models.user.UserInfo;
import io.github.umangjpatel.gallop.models.user.UserInfoBuilder;

public class UserInfoRepository {

    private static UserInfoRepository sUserInfoRepository;

    private DatabaseReference mDatabase;

    private UserInfoRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static UserInfoRepository getInstance() {
        if (sUserInfoRepository == null)
            sUserInfoRepository = new UserInfoRepository();
        return sUserInfoRepository;
    }

    public void addUser(FirebaseUser currentUser) {
        UserInfo userInfo = getUserDetails(currentUser);
        mDatabase
                .child("users")
                .child(userInfo.getDisplayName())
                .setValue(userInfo);
    }

    private UserInfo getUserDetails(@NonNull FirebaseUser currentUser) {
        return new UserInfoBuilder()
                .setEmailAddress(currentUser.getEmail())
                .createUserInfo();
    }

    public UserInfo readUserFromDatabase(@NonNull FirebaseUser currentUser) {
        return new UserInfoBuilder()
                .setEmailAddress(currentUser.getEmail())
                .createUserInfo();
    }
}
