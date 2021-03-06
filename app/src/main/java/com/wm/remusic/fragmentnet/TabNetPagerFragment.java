package com.wm.remusic.fragmentnet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wm.remusic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wm on 2016/4/11.
 */
public class TabNetPagerFragment extends Fragment implements ChangeView {
    //PreferencesUtility mPreferences;
    private ViewPager viewPager;
    private int page = 0;
    private ActionBar ab;
    private String[] title;

    public static final TabNetPagerFragment newInstance(int page, String[] title) {
        TabNetPagerFragment f = new TabNetPagerFragment();
        Bundle bdl = new Bundle(1);
        bdl.putInt("page_number", page);
        f.setArguments(bdl);
        return f;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager.setCurrentItem(page);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_net_tab, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
            viewPager.setOffscreenPageLimit(2);
        }

        final TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

        new Thread(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        }).start();


        return rootView;

    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        RecommendFragment fragment = new RecommendFragment();
        fragment.setChanger(this);
        adapter.addFragment(fragment, "新曲");
        adapter.addFragment(new AllPlaylistFragment(), "歌单");
        //  adapter.addFragment(new NetFragment(), "主播电台");
        adapter.addFragment(new RankingFragment(), "排行榜");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void changeTo(int page) {
        if (viewPager != null)
            viewPager.setCurrentItem(page);
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}

