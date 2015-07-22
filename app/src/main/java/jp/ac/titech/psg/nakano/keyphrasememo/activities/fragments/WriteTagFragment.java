package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        List<String> tags = parent.getDefaultTags();
        if(tags != null && !tags.isEmpty()) {
            for (String s : tags) {
                parent.createTagView(s);
            }
        }

        // set listener
        Button getTagButton = (Button) parent.findViewById(R.id.fragment_write_memo_getTag);
        getTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click getTagButton");
                String content = parent.getMemoContent();
                GetTagTask task = new GetTagTask(parent);
                task.execute(content);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write_tag, container, false);
    }
}
