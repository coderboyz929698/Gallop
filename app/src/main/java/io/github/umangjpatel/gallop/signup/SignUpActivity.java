package io.github.umangjpatel.gallop.signup;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.databinding.ActivitySignupBinding;
import io.github.umangjpatel.gallop.main.MainActivity;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignupBinding mSignUpBinding;
    private SignUpViewModel mSignUpViewModel;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        mSignUpBinding.setLifecycleOwner(this);
        mSignUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        mSignUpBinding.setSignUpViewModel(mSignUpViewModel);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(mAuth.getCurrentUser());
    }

    public void startUserLogin(View view) {
        mAuth.signInWithEmailAndPassword(mSignUpViewModel.getUserEmail(), mSignUpViewModel.getPassword())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                        updateUI(mAuth.getCurrentUser());
                    else
                        showErrorMessages();
                });
    }

    private void showErrorMessages() {
        mSignUpBinding.emailAddressInputLayout.setError(getResources().getString(R.string.error_email));
        mSignUpBinding.passwordInputLayout.setError(getResources().getString(R.string.error_password));
        mSignUpBinding.passwordEditText.setText(null);
    }


    public void createUserAccount(View view) {
        mAuth.createUserWithEmailAndPassword(mSignUpViewModel.getUserEmail(), mSignUpViewModel.getPassword())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                        updateUI(mAuth.getCurrentUser());
                    else
                        showErrorMessages();
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(MainActivity.newIntent(this));
            finish();
        }
    }

}
