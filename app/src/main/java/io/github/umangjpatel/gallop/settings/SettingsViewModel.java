package io.github.umangjpatel.gallop.settings;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

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

    public void fetchUserData() {
        mUserInfoLiveData.setValue(mUserInfoRepository.readUserFromDatabase());
    }
}
