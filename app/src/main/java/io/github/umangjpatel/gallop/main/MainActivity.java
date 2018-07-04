package io.github.umangjpatel.gallop.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.catalog.CatalogFragment;
import io.github.umangjpatel.gallop.dashboard.DashboardFragment;
import io.github.umangjpatel.gallop.databinding.ActivityMainBinding;
import io.github.umangjpatel.gallop.quizzes.QuizzesFragment;
import io.github.umangjpatel.gallop.settings.SettingsFragment;
import io.github.umangjpatel.gallop.utils.adapters.viewpager.BottomBarAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        int position = 0;
        switch (item.getItemId()) {
            case R.id.navigation_dashboard:
                position = 0;
                break;
            case R.id.navigation_catalog:
                position = 1;
                break;
            case R.id.navigation_quizzes:
                position = 2;
                break;
            case R.id.navigation_settings:
                position = 3;
                break;
        }
        mMainBinding.fragmentViewPager.setCurrentItem(position, true);
        return true;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBinding();
        setupNavigation();
        setupViewPager();
    }

    private void setupViewPager() {
        mMainBinding.fragmentViewPager.setPagingEnabled(false);
        BottomBarAdapter pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(DashboardFragment.newInstance());
        pagerAdapter.addFragment(CatalogFragment.newInstance());
        pagerAdapter.addFragment(QuizzesFragment.newInstance());
        pagerAdapter.addFragment(SettingsFragment.newInstance());
        mMainBinding.fragmentViewPager.setAdapter(pagerAdapter);
    }

    private void setupNavigation() {
        mMainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mMainBinding.navigation.setSelectedItemId(R.id.navigation_dashboard);
    }

    private void setupBinding() {
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setLifecycleOwner(this);
    }

    @NonNull
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }

}
