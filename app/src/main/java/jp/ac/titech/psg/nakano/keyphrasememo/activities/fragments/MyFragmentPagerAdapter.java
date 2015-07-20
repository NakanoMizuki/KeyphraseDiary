package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(android.support.v4.app.FragmentManager fm){
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position){
        switch (position){
            default:
                return new WriteMemoFragment();
        }
    }

    @Override
    public int getCount(){
        return 1;
    }

}
