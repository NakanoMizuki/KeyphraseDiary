package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments.search;

import android.os.Bundle;

import java.util.List;

import jp.ac.titech.psg.nakano.keyphrasememo.database.TableConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.model.Tag;

/**
 * Created by nakanomizuki on 15/07/21.
 */
public class TagListFragment extends android.support.v4.app.ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);

        TableConnector tableConnector = new TableConnector(getActivity());
        List<Tag> tags = tableConnector.getAllTag();
        TagListAdapter adapter = new TagListAdapter(getActivity(), 0, tags);
        setListAdapter(adapter);
    }
}
