package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final int NUM_PAGE = 3;
    private static final String[] TITLES = {"メモ", "タグ", "プレビュー"};

    public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){
        switch (position){
            case 0:
                return new WriteMemoFragment();
            case 1:
                return new WriteTagFragment();
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
