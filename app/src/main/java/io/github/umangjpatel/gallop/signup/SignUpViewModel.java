package io.github.umangjpatel.gallop.signup;

import android.arch.lifecycle.ViewModel;

public class SignUpViewModel extends ViewModel {

    private String mUserEmail, mPassword;

    public void saveUserEmail(CharSequence s) {
        mUserEmail = s.toString();
    }

    public void saveUserPassword(CharSequence s) {
        mPassword = s.toString();
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public String getPassword() {
        return mPassword;
    }
}
