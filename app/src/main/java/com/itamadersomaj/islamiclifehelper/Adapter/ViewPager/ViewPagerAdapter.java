package com.itamadersomaj.islamiclifehelper.Adapter.ViewPager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<String> fragmentTitle;
    private final List<Fragment> fragments;
    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = new ArrayList();
        this.fragmentTitle = new ArrayList();
    }
    public void add(Fragment fragment, String str) {
        this.fragments.add(fragment);
        this.fragmentTitle.add(str);
    }
    @Override 
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override 
    public int getCount() {
        return this.fragments.size();
    }

    @Override 
    public CharSequence getPageTitle(int i) {
        return this.fragmentTitle.get(i);
    }
}
