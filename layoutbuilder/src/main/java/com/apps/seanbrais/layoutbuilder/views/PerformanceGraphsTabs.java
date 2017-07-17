package com.apps.seanbrais.layoutbuilder.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.apps.seanbrais.layoutbuilder.R;

import java.util.ArrayList;
import java.util.List;

public abstract class PerformanceGraphsTabs extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle data;
    public ArrayList<Fragment> fragments = new ArrayList<>();
    public ArrayList<String> fragmentNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_stats_tabs);
        data = this.getIntent().getExtras();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        addTabs();
        setupViewPager(viewPager);

    }

    public abstract void addTabs();

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for(int i = 0; i < fragments.size(); i++){
            this.fragments.get(i).setArguments(data);
            adapter.addFragment(this.fragments.get(i), this.fragmentNames.get(i));
        }
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

