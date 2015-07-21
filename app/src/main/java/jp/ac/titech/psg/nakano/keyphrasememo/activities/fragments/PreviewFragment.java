package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;

public class PreviewFragment extends android.support.v4.app.Fragment {


    public static PreviewFragment newInstance() {
        PreviewFragment fragment = new PreviewFragment();
        return fragment;
    }

    public PreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        final AbstractWriteActivity parent = (AbstractWriteActivity) getActivity();
        parent.getViewPager().setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                String title = parent.getMemoTitle();
                TextView titleView = (TextView) parent.findViewById(R.id.preview_fragment_title);
                titleView.setText(title);
                String content = parent.getMemoContent();
                TextView contentView = (TextView) parent.findViewById(R.id.preview_fragment_content);
                contentView.setText(content);

                List<String> tags = parent.getTagNames();
                if(tags != null && !tags.isEmpty()) {
                    TextView tagView = (TextView) parent.findViewById(R.id.preview_fragment_tag);
                    String str = "";
                    for (String tag : tags) {
                        str += tag + ",";
                    }
                    tagView.setText(str);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }


}
