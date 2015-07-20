package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;
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
        View view = inflater.inflate(R.layout.fragment_write_memo, container, false);
        if (getArguments() != null) {
            Memo memo = (Memo) getArguments().getSerializable(ARG_PARAM);
            ((EditText) view.findViewById(R.id.fragment_write_memo_title)).setText(memo.getTitle());
            ((EditText) view.findViewById(R.id.fragment_write_memo_content)).setText(memo.getContent());
        }
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        final AbstractWriteActivity parent = (AbstractWriteActivity) getActivity();
        EditText editTitle = (EditText) parent.findViewById(R.id.fragment_write_memo_title);
        editTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                parent.setMemoTitle(s.toString());
            }
        });
        EditText editContent = (EditText) parent.findViewById(R.id.fragment_write_memo_content);
        editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                parent.setMemoContent(s.toString());
            }
        });
    }

}
