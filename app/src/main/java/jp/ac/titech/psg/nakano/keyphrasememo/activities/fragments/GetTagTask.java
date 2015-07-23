package jp.ac.titech.psg.nakano.keyphrasememo.activities.fragments;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Set;

import jp.ac.titech.psg.nakano.keyphrasememo.R;
import jp.ac.titech.psg.nakano.keyphrasememo.YahooConnector;
import jp.ac.titech.psg.nakano.keyphrasememo.activities.AbstractWriteActivity;

/**
 * Created by nakanomizuki on 15/07/20.
 */
public class GetTagTask extends AsyncTask<String, Void, Set<String>> {
    private final AbstractWriteActivity activity;

    public GetTagTask(AbstractWriteActivity activity){
        this.activity = activity;
    }

    @Override
    public Set<String> doInBackground(String... params){
        String content =params[0];
        if(content == null || content.equals("")) return null;
        return YahooConnector.getKeyphrase(content);
    }

    @Override
    public void onPostExecute(Set<String> result){
        if(result == null || result.isEmpty()){
            Toast.makeText(activity, R.string.fail_getting_keyphrase, Toast.LENGTH_SHORT).show();
            return;
        }
        String str = "";
        for(String s: result){
            activity.createTagView(s);
            str += s + ",";
        }
        str = str.substring(0, str.length()-1);
        Toast.makeText(activity, "タグの取得に成功しました:" + str, Toast.LENGTH_SHORT).show();
    }

}
