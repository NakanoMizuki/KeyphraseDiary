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


public class WriteMemoFragment extends android.support.v4.app.Fragment {

    public static WriteMemoFragment newInstance() {
        WriteMemoFragment fragment = new WriteMemoFragment();
        return fragment;
    }

    public WriteMemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write_memo, container, false);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        final AbstractWriteActivity parent = (AbstractWriteActivity) getActivity();

        // set default value
        ((EditText) parent.findViewById(R.id.fragment_write_memo_title)).setText(parent.getMemoTitle());
        ((EditText) parent.findViewById(R.id.fragment_write_memo_content)).setText(parent.getMemoContent());

        // set listener
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
