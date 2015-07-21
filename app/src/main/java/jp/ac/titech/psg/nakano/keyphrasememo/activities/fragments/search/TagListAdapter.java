package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/21.
 */
public class TagListAdapter extends ArrayAdapter<Tag> {
    private LayoutInflater mLayoutInflater;
    private List<Boolean> checkedList;

    public TagListAdapter(Context context, int resourceId, List<Tag> data){
        super(context, resourceId, data);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        checkedList = new ArrayList<Boolean>();
        for(int i=0; i < data.size(); i++){
            checkedList.add(false);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.taglistitem, null);
        }

        Tag item = (Tag) getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.taglist_chckbox);
        textView.setText(item.getName());

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.taglist_chckbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedList.set(position, isChecked);
            }
        });

        return convertView;
    }

    public List<Boolean> getCheckedList(){
        return checkedList;
    }
}
