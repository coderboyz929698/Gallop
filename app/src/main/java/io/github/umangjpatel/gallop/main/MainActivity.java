package io.github.umangjpatel.gallop.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import io.github.umangjpatel.gallop.R;
import io.github.umangjpatel.gallop.catalog.CatalogFragment;
import io.github.umangjpatel.gallop.dashboard.DashboardFragment;
import io.github.umangjpatel.gallop.databinding.ActivityMainBinding;
import io.github.umangjpatel.gallop.questions.list.QuestionsListFragment;
import io.github.umangjpatel.gallop.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private String KEY_NAV_INDEX = MainActivity.class.getSimpleName() + ".key_nav_index";
    private int mNavIndex;

    private ActivityMainBinding mMainBinding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> loadFragment(item.getItemId());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBinding();
        setupNavigation(savedInstanceState);
    }

    private void setupBinding() {
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.setLifecycleOwner(this);
    }

    private void setupNavigation(Bundle savedInstanceState) {
        mMainBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mMainBinding.navigation.setSelectedItemId(savedInstanceState != null ? mNavIndex : R.id.navigation_dashboard);
    }

    private boolean loadFragment(int itemId) {
        mNavIndex = itemId;
        Fragment fragment;
        switch (itemId) {
            case R.id.navigation_dashboard:
                fragment = DashboardFragment.newInstance();
                break;
            case R.id.navigation_catalog:
                fragment = CatalogFragment.newInstance();
                break;
            case R.id.navigation_questions:
                fragment = QuestionsListFragment.newInstance();
                break;
            case R.id.navigation_settings:
                fragment = SettingsFragment.newInstance();
                break;
            default:
                return false;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NAV_INDEX, mNavIndex);
    }

    @NonNull
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }

}
