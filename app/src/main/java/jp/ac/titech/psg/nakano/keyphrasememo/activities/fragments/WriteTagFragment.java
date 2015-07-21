package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;

public class WriteTagFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "WriteTagFragment";

    private AbstractWriteActivity parent;

    public static WriteTagFragment newInstance() {
        WriteTagFragment fragment = new WriteTagFragment();
        return fragment;
    }

    public WriteTagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
        parent = (AbstractWriteActivity) getActivity();

        // set default value
        List<String> tags = parent.getTags();
        if(tags != null && !tags.isEmpty()) {
            String str = "";
            for (String s : tags) {
                str += s + ",";
            }
            ((EditText) parent.findViewById(R.id.fragment_write_memo_tag)).setText(str);
        }

        // set listener
        Button getTagButton = (Button) parent.findViewById(R.id.fragment_write_memo_getTag);
        getTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click getTagButton");
                EditText tags = (EditText) parent.findViewById(R.id.fragment_write_memo_tag);
                String content = parent.getMemoContent();
                GetTagTask task = new GetTagTask(parent, tags);
                task.execute(content);
            }
        });
        EditText tagEdit = (EditText) parent.findViewById(R.id.fragment_write_memo_tag);
        tagEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                List<String> tags = Arrays.asList(s.toString().split(","));
                parent.setTags(tags);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write_tag, container, false);
    }
}
