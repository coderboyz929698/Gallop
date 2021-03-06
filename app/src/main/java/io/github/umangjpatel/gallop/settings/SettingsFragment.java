package io.github.umangjpatel.gallop.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding mSettingsBinding;
    private SettingsViewModel mSettingsViewModel;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSettingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        mSettingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        mSettingsViewModel.fetchUserData(FirebaseAuth.getInstance().getCurrentUser());
        mSettingsViewModel.getUserInfoLiveData().observe(this, userInfo -> {
            if (userInfo != null) {
                mSettingsBinding.userDisplayNameTextView.setText(userInfo.getDisplayName());
                mSettingsBinding.userEmailTextView.setText(userInfo.getEmailAddress());
            }
        });
        return mSettingsBinding.getRoot();
    }
}
