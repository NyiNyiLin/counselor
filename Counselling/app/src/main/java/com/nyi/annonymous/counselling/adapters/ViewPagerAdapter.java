package com.nyi.annonymous.counselling.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nyi.annonymous.counselling.fragments.LeaderBoardFragment;
import com.nyi.annonymous.counselling.fragments.LiveFeedFragment;

/**
 * Created by IN-3442 on 11-Oct-15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return LiveFeedFragment.newInstance();
        }
        else if(position == 1){
            return new Fragment();
        }
        else {
            return LeaderBoardFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0) return "Live Feed";
        else if(position == 1) return "Chats";
        else return "LeaderBoards";
    }
}
