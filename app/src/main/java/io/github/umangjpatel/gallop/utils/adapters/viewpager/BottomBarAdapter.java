package io.github.umangjpatel.gallop.utils.adapters.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class BottomBarAdapter extends SmartFragmentStatePagerAdapter {

    private final List<Fragment> mFragmentsList = new ArrayList<>();

    public BottomBarAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment) {
        mFragmentsList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }
}
