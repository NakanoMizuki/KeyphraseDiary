package jp.ac.titech.psg.nakano.keyphrasememo.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;

public class MemoFragment extends Fragment {
    public static final String ARG_PARAM = "memo";


    public static MemoFragment newInstance(Memo m) {
        MemoFragment fragment = new MemoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, m);
        fragment.setArguments(args);
        return fragment;
    }

    public MemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);
        if (getArguments() != null) {
            Memo memo = (Memo) getArguments().getSerializable(ARG_PARAM);
            ((TextView) view.findViewById(R.id.memo_fragment_title)).setText(memo.getTitle());
        }
        return view;
    }


}
