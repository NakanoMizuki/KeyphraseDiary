package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

public class ReadMemoFragment extends Fragment {
    public static final String ARG_PARAM = "memo";


    public static ReadMemoFragment newInstance(Memo m) {
        ReadMemoFragment fragment = new ReadMemoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, m);
        fragment.setArguments(args);
        return fragment;
    }

    public ReadMemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_memo, container, false);
        if (getArguments() != null) {
            Memo memo = (Memo) getArguments().getSerializable(ARG_PARAM);
            ((TextView) view.findViewById(R.id.read_memo_fragment_id)).setText(memo.getId() + "");
            ((TextView) view.findViewById(R.id.read_memo_fragment_title)).setText(memo.getTitle());
            String tagText = "";
            for (Tag tag : memo.getTags()){
                tagText += tag.getName() + ",";
            }
            ((TextView) view.findViewById(R.id.read_memo_fragment_tag)).setText(tagText);
        }
        return view;
    }


}
