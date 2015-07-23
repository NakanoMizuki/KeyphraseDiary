package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Memo;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/23.
 */
public class MemoListAdapter extends ArrayAdapter<Memo> {

    private Context _context;
    private int _textViewResourceId;
    private List<Memo> _items;
    private LayoutInflater _inflater;

    public MemoListAdapter(Context context, int textViewResourceId, List<Memo> items) {
        super(context, textViewResourceId, items);

        _context = context;
        _textViewResourceId = textViewResourceId;
        _items = items;

        _inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = _inflater.inflate(_textViewResourceId, null);
        }

        Memo item = _items.get(position);

        ((TextView) view.findViewById(R.id.row_title)).setText(item.getTitle());
        String str = "";
        for (Tag tag:item.getTags()){
            str += tag.getName() + ",";
        }
        if(item.getTags().size() != 0){
            str =str.substring(0, str.length() -1);
        }
        ((TextView) view.findViewById(R.id.row_tags)).setText(str);

        return view;
    }
}
