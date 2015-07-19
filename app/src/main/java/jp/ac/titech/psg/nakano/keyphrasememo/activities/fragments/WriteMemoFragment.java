package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.YahooConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;


public class WriteMemoFragment extends Fragment {

    private static final String TAG = "WriteMemoFragment";
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
    public void onStart(){
        super.onStart();

        // set listener
        Button getTagButton = (Button) getActivity().findViewById(R.id.fragment_write_memo_getTag);
        getTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click getTagButton");
                String text = ((EditText) getActivity().findViewById(R.id.fragment_write_memo_content)).getText().toString();
                YahooConnector.getKeyphrase(text, WriteMemoFragment.this);
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
//
//    public void arriveKeyphrase(List<String> keyphrase){
//        Log.d(TAG, "arriveKeyphrase");
//        EditText tags = (EditText) getActivity().findViewById(R.id.fragment_write_memo_tag);
//        String str = "";
//        for(String k:keyphrase){
//            str += k;
//            str += ", ";
//        }
//        //tags.setText(str);
//        Log.d(TAG, str);
//    }

    public void failGettingKeyphrase(){
        Toast.makeText(getActivity(), "キーフレーズの取得に失敗しました。", Toast.LENGTH_SHORT);
    }
}
