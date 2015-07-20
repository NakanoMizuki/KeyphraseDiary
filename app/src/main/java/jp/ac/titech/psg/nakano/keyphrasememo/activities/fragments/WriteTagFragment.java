package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;

public class WriteTagFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "WriteTagFragment";
    private static final String ARG_PARAM = "tag";

    private AbstractWriteActivity parent;
    private String[] tags;

    public static WriteTagFragment newInstance(String[] tags) {
        WriteTagFragment fragment = new WriteTagFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, tags);
        fragment.setArguments(args);
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

        // set listener
        Button getTagButton = (Button) parent.findViewById(R.id.fragment_write_memo_getTag);
        getTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click getTagButton");
                EditText tags = (EditText) parent.findViewById(R.id.fragment_write_memo_tag);
                String content = ((EditText) parent.findViewById(R.id.fragment_write_memo_content))
                        .getText().toString();
                GetTagTask task = new GetTagTask(parent, tags);
                task.execute(content);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (getArguments() != null) {
            tags = (String[]) getArguments().getSerializable(ARG_PARAM);
        }
        return inflater.inflate(R.layout.fragment_write_tag, container, false);
    }


}
