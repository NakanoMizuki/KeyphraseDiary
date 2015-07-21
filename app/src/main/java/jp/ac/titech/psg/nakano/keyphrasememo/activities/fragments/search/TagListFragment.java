package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.search;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/21.
 */
public class TagListFragment extends android.support.v4.app.ListFragment {
    private List<Tag> tags;
    private TagListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);

        TableConnector tableConnector = new TableConnector(getActivity());
        tags = tableConnector.getAllTag();
        adapter = new TagListAdapter(getActivity(), 0, tags);
        setListAdapter(adapter);
    }

    public ArrayList<String> getCheckedTagIds(){
        ArrayList<String> tagIds = new ArrayList<String>();
        List<Boolean> checkedList = adapter.getCheckedList();
        for(int i=0; i < checkedList.size(); i++){
            if(checkedList.get(i)){
                tagIds.add(String.valueOf(tags.get(i).getId()));
            }
        }
        return tagIds;
    }
}
