package com.sjy.sndroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * viewpager基础适配器
 * Created by sjy on 2017/6/26.
 */

public class ViewPagerCommonAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String[] titles;
    private float percentItem=1.0f;//子项所占百分比
    public ViewPagerCommonAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerCommonAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public ViewPagerCommonAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] titles, float percentItem) {
        super(fm);
        this.list = list;
        this.titles = titles;
        this.percentItem = percentItem;
    }

    @Override
    public float getPageWidth(int position) {
        return percentItem;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles!=null){
        return titles[position];
        }else {
            return "";
        }
    }
}
