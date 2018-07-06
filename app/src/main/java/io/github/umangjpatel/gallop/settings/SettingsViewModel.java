package io.github.umangjpatel.gallop.settings;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import io.github.umangjpatel.gallop.models.user.UserInfo;
import io.github.umangjpatel.gallop.repositories.UserInfoRepository;

public class SettingsViewModel extends AndroidViewModel {

    private UserInfoRepository mUserInfoRepository;
    private MutableLiveData<UserInfo> mUserInfoLiveData = new MutableLiveData<>();

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        mUserInfoRepository = UserInfoRepository.getInstance();
    }

    public MutableLiveData<UserInfo> getUserInfoLiveData() {
        return mUserInfoLiveData;
    }

    public void fetchUserData(FirebaseUser currentUser) {
        mUserInfoLiveData.setValue(mUserInfoRepository.readUserFromDatabase(currentUser));
    }
}
