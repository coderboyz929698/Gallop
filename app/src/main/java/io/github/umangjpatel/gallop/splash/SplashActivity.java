package io.github.umangjpatel.gallop.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.signup.SignUpActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        startActivity(SignUpActivity.newIntent(this));
        finish();
    }
}
