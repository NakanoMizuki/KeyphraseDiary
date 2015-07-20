package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGE = 2;

    public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){
        switch (position){
            case 0:
                return new WriteMemoFragment();
            default:
                return new WriteTagFragment();
        }
    }

    @Override
    public int getCount(){
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "メモ";
            default:
                return "タグ";
        }
    }

}
