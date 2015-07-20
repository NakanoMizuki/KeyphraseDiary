package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;


public class WriteMemoFragment extends Fragment {

    private static final String TAG = "WriteMemoFragment";
    private static final String ARG_PARAM = "memo";
    private Activity parent;

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
    public void onStart(){
        super.onStart();
        parent = getActivity();

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

        View view = inflater.inflate(R.layout.fragment_write_memo, container, false);
        if (getArguments() != null) {
            Memo memo = (Memo) getArguments().getSerializable(ARG_PARAM);
            ((EditText) view.findViewById(R.id.fragment_write_memo_title)).setText(memo.getTitle());
            ((EditText) view.findViewById(R.id.fragment_write_memo_content)).setText(memo.getContent());
        }
        return view;
    }

}
