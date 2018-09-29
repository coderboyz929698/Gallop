package io.github.umangjpatel.gallop.signup;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import io.github.umangjpatel.gallop.repositories.UserInfoRepository;

public class SignUpViewModel extends AndroidViewModel {

    private UserInfoRepository mUserInfoRepository;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        mUserInfoRepository = UserInfoRepository.getInstance();
    }

    public void registerUser(FirebaseUser currentUser) {
        mUserInfoRepository.addUser(currentUser);
    }
}
