package io.github.umangjpatel.gallop.signup;

import android.app.Application;

import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.github.umangjpatel.gallop.repositories.UserInfoRepository;

public class SignUpViewModel extends AndroidViewModel {

    private UserInfoRepository mUserInfoRepository;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        mUserInfoRepository = UserInfoRepository.getInstance();
    }

    void registerUser(FirebaseUser currentUser) {
        mUserInfoRepository.addUser(currentUser);
    }
}
