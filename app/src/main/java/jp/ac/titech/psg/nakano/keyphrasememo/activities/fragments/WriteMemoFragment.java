package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;


public class WriteMemoFragment extends android.support.v4.app.Fragment {
    private static final String ARG_PARAM = "memo";

    public static WriteMemoFragment newInstance(Memo memo) {
        WriteMemoFragment fragment = new WriteMemoFragment();
        if(memo == null) return  fragment;
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, memo);
        fragment.setArguments(args);
        return fragment;
    }

    public WriteMemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_write_memo, container, false);
        if (getArguments() != null) {
            Memo memo = (Memo) getArguments().getSerializable(ARG_PARAM);
            ((EditText) view.findViewById(R.id.fragment_write_memo_title)).setText(memo.getTitle());
            ((EditText) view.findViewById(R.id.fragment_write_memo_content)).setText(memo.getContent());
        }
        return view;
    }

}
