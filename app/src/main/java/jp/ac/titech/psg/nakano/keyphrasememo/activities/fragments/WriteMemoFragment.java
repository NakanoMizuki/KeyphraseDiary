package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Hashtable;
import java.util.Map;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;


public class WriteMemoFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private static final Map<Integer, String> mdMap = new Hashtable<Integer, String>(){{
        put(R.id.fragment_write_memo_h1, "# ");
        put(R.id.fragment_write_memo_h2, "## ");
        put(R.id.fragment_write_memo_h3, "### ");
        put(R.id.fragment_write_memo_list, "- ");
    }};

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
        final EditText editContent = (EditText) parent.findViewById(R.id.fragment_write_memo_content);
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
        parent.findViewById(R.id.fragment_write_memo_h1).setOnClickListener(this);
        parent.findViewById(R.id.fragment_write_memo_h2).setOnClickListener(this);
        parent.findViewById(R.id.fragment_write_memo_h3).setOnClickListener(this);
        parent.findViewById(R.id.fragment_write_memo_list).setOnClickListener(this);
    }

   @Override
   public void onClick(View v) {
       int id = v.getId();
       String insert = mdMap.get(id);
       EditText editText = (EditText) getActivity().findViewById(R.id.fragment_write_memo_content);
       int start = editText.getSelectionStart();
       int end = editText.getSelectionEnd();
       Editable editable = editText.getText();
       editable.replace( Math.min( start, end ), Math.max( start, end ), insert);
   }

}
