package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
        parent.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                // hide input
                Activity activity = getActivity();
                View focusView = activity.getCurrentFocus();
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // set title with underline
                String title = parent.getMemoTitle();
                TextView titleView = (TextView) parent.findViewById(R.id.preview_fragment_title);
                if(title != null){
                    Spannable t = Spannable.Factory.getInstance().newSpannable(title);
                    UnderlineSpan us = new UnderlineSpan();
                    t.setSpan(us, 0, title.length(), t.getSpanFlags(us));
                    titleView.setText(t, TextView.BufferType.SPANNABLE);
                }else{
                    titleView.setText(title);
                }

                String content = parent.getMemoContent();
                ViewGroup contentView = (ViewGroup) parent.findViewById(R.id.preview_fragment_content);
                new MarkDownViewCreator(parent).createMarkDownView(content);

                List<String> tags = parent.getTagNames();
                TextView tagView = (TextView) parent.findViewById(R.id.preview_fragment_tag);
                String str = getString(R.string.tag_prefix);
                if(tags != null && !tags.isEmpty()) {
                    for (String tag : tags) {
                        str += tag + ",";
                    }
                    str = str.substring(0, str.length() - 1);
                    tagView.setText(str);
                }else{
                    tagView.setText(str);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }


}
