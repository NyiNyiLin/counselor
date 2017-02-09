package com.nyi.annonymous.counselling.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.viewpager)
    ViewPager vpMain;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getFragmentManager());
        vpMain.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(vpMain);

        return view;
    }
}
