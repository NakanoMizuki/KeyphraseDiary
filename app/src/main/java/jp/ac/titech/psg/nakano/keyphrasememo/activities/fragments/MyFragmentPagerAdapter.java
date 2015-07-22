package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGE = 3;
    private static final int DEFAULT_PAGE = 1;
    private static final String[] TITLES = {"タグ", "メモ", "プレビュー"};

    public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }

    public int getDefaultPage(){
        return DEFAULT_PAGE;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){
        switch (position){
            case 0:
                return new WriteTagFragment();
            case 1:
                return new WriteMemoFragment();
            default:
                return new PreviewFragment();
        }
    }

    @Override
    public int getCount(){
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return TITLES[position];
    }

}
